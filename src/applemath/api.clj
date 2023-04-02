(ns applemath.api
  (:require [tech.v3.datatype :as dt]
            [tech.v3.resource :as resource]
            [tech.v3.datatype.struct :as struct]
            [tech.v3.datatype.ffi :as dt-ffi]
            [applemath.ffi :as ffi])
  (:import [tech.v3.datatype.ffi Pointer]))



(defn initialize!
  ([] (ffi/initialize!))
  ([options] (ffi/initialize! options)))

(defn all-distributions
  []
  {:uniform '[a b]
   :gaussian '[a sigma]})


(defn- ptr-address
  ^long [data] (.address ^Pointer (dt-ffi/->pointer data)))

(defn rng-stream
  "Return a function that when called returns a new batch of random numbers.  Returns the same native buffer
  on every call.  The values of (all-distributions) state the name of the optional arguments and their
  order.

  Options:

  * `:seed` - unsigned 64 bit integer - defaults to System/nanoTime.
  * `:dist` - either :uniform or :gaussian.
"
  [^long n {:keys [seed dist]
            :or {dist :uniform}
            :as options}]
  (let [seed (long (or seed (System/nanoTime)))
        argvec (get (all-distributions) dist)
        argvals (mapv (fn [argsym]
                        (if-let [argval (get options (keyword (name argsym)))]
                          argval
                          (throw (RuntimeException. (format "Failed for find arguments %s for dist %s"
                                                            argsym dist)))))
                      argvec)
        stream (ffi/BNNSCreateRandomGeneratorWithSeed 0 seed nil)
        rval (dt/alloc-uninitialized :float32 n)
        ^java.util.Map array-desc (struct/new-struct (:datatype-name @ffi/array-desc-type*)
                                                     {:container-type :native-heap
                                                      :resource-type :auto})
        _ (.put array-desc :layout 0x10000)
        _ (.put array-desc [:size 0] n)
        _ (.put array-desc [:stride 0] 1)
        _ (.put array-desc :data (ptr-address rval))
        _ (.put array-desc :data-type (ffi/bnns-datatypes :float32))
        rnd-fn (case dist
                 :uniform (fn []
                            (ffi/BNNSRandomFillUniformFloat stream array-desc (argvals 0) (argvals 1))
                            rval)
                 :gaussian (fn []
                            (ffi/BNNSRandomFillNormalFloat stream array-desc (argvals 0) (argvals 1))
                             rval))]
    (resource/track
     (case dist
       :uniform (fn []
                  (ffi/BNNSRandomFillUniformFloat stream array-desc (argvals 0) (argvals 1))
                  rval)
       :gaussian (fn []
                   (ffi/BNNSRandomFillNormalFloat stream array-desc (argvals 0) (argvals 1))
                   rval))
     {:track-type :auto
      :dispose-fn #(ffi/BNNSDestroyRandomGenerator stream)})))


(comment
  (initialize!)
  (def f (rng-stream 1024 {:dist :uniform :a 0.0 :b 1.0}))
  )
