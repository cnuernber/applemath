(ns applemath.ffi
  (:require [tech.v3.datatype.ffi :as dt-ffi]
            [tech.v3.datatype.ffi.size-t :as dt-size-t]
            [tech.v3.datatype.struct :as dt-struct]))



;; /Library/Developer/CommandLineTools/SDKs/MacOSX13.3.sdk/System/Library/Frameworks/Accelerate.framework/Versions/A/Frameworks/vecLib.framework/Versions/A/Headers/BNNS/bnns.h


;; BNNS_ENUM(BNNSDataLayout, uint32_t,

;;   // 1-dimensional layouts (are identical and only included for ease of use and consistent numbering)
;;   BNNSDataLayoutVector                         __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x10000,
;;   BNNSDataLayout1DLastMajor                    __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x18000,
;;   BNNSDataLayout1DFirstMajor                   __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x18001,

;;   // 2-dimensional layouts
;;   BNNSDataLayoutRowMajorMatrix                 __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x20000,
;;   BNNSDataLayoutColumnMajorMatrix              __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x20001,
;;   BNNSDataLayout2DLastMajor                    __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x28000,
;;   BNNSDataLayout2DFirstMajor                   __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x28001,
;;   BNNSDataLayoutFullyConnectedSparse           __API_AVAILABLE(macos(13.0), ios(16.0), watchos(9.0), tvos(16.0)) = 0x21001,

;;   // 3-dimensional layouts
;;   BNNSDataLayoutImageCHW                       __API_AVAILABLE(macos(10.15), ios(13.0), watchos(6.0), tvos(13.0)) = 0x30000,
;;   BNNSDataLayoutSNE                            __API_AVAILABLE(macos(12.0),  ios(15.0), watchos(8.0), tvos(15.0)) = 0x30001,
;;   BNNSDataLayoutNSE                            __API_AVAILABLE(macos(12.0),  ios(15.0), watchos(8.0), tvos(15.0)) = 0x30002,
;;   BNNSDataLayoutMHA_DHK                        __API_AVAILABLE(macos(13.0),  ios(16.0), watchos(9.0), tvos(16.0)) = 0x30003,
;;   BNNSDataLayout3DLastMajor                    __API_AVAILABLE(macos(11.0),  ios(14.0), watchos(7.0), tvos(14.0)) = 0x38000,
;;   BNNSDataLayout3DFirstMajor                   __API_AVAILABLE(macos(11.0),  ios(14.0), watchos(7.0), tvos(14.0)) = 0x38001,

;;   // 4-dimensional layouts
;;   BNNSDataLayoutConvolutionWeightsOIHW         __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0),  tvos(14.0)) = 0x40000,
;;   BNNSDataLayoutConvolutionWeightsOIHrWr       __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0),  tvos(14.0)) = 0x40001,
;;   BNNSDataLayoutConvolutionWeightsIOHrWr       __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0),  tvos(14.0)) = 0x40002,
;;   BNNSDataLayoutConvolutionWeightsOIHW_Pack32  __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0),  tvos(14.0)) = 0x40010,
;;   BNNSDataLayout4DLastMajor                    __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0),  tvos(14.0)) = 0x48000,
;;   BNNSDataLayout4DFirstMajor                   __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0),  tvos(14.0)) = 0x48001,

;;   // 5-dimensional layouts
;;   BNNSDataLayout5DLastMajor                    __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x58000,
;;   BNNSDataLayout5DFirstMajor                   __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x58001,

;;   // 6-dimensional layouts
;;   BNNSDataLayout6DLastMajor                    __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x68000,
;;   BNNSDataLayout6DFirstMajor                   __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x68001,

;;   // 7-dimensional layouts
;;   BNNSDataLayout7DLastMajor                    __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x78000,
;;   BNNSDataLayout7DFirstMajor                   __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x78001,

;;   // 8-dimensional layouts
;;   BNNSDataLayout8DLastMajor                    __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x88000,
;;   BNNSDataLayout8DFirstMajor                   __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x88001,
;; );

;; BNNS_ENUM(BNNSDataType, uint32_t,

(def BNNSDataTypeFloatBit 0x10000)
;;   BNNSDataTypeFloat16          __API_AVAILABLE(macos(10.12), ios(10.0), watchos(3.0), tvos(10.0)) = BNNSDataTypeFloatBit | 16,
(def BNNSDataTypeFloat32          (bit-or BNNSDataTypeFloatBit 32))
;;   BNNSDataTypeBFloat16         __API_AVAILABLE(macos(12.0),  ios(15.0), watchos(8.0), tvos(15.0)) = BNNSDataTypeFloatBit | 0x8000 | 16,

;;   BNNSDataTypeIntBit           __API_AVAILABLE(macos(10.12), ios(10.0), watchos(3.0), tvos(10.0)) = 0x20000,
;;   BNNSDataTypeInt1             __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeIntBit | 1,
;;   BNNSDataTypeInt2             __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeIntBit | 2,
;;   BNNSDataTypeInt4             __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeIntBit | 4,
;;   BNNSDataTypeInt8             __API_AVAILABLE(macos(10.12), ios(10.0), watchos(3.0), tvos(10.0)) = BNNSDataTypeIntBit | 8,
;;   BNNSDataTypeInt16            __API_AVAILABLE(macos(10.12), ios(10.0), watchos(3.0), tvos(10.0)) = BNNSDataTypeIntBit | 16,
;;   BNNSDataTypeInt32            __API_AVAILABLE(macos(10.12), ios(10.0), watchos(3.0), tvos(10.0)) = BNNSDataTypeIntBit | 32,
;;   BNNSDataTypeInt64            __API_AVAILABLE(macos(12.0), ios(15.0), watchos(8.0), tvos(15.0)) = BNNSDataTypeIntBit | 64,

;;   BNNSDataTypeUIntBit          __API_AVAILABLE(macos(10.13), ios(11.0), watchos(4.0), tvos(11.0)) = 0x40000,
;;   BNNSDataTypeUInt1            __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeUIntBit | 1,
;;   BNNSDataTypeUInt2            __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeUIntBit | 2,
;;   BNNSDataTypeUInt4            __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeUIntBit | 4,
;;   BNNSDataTypeUInt8            __API_AVAILABLE(macos(10.13), ios(11.0), watchos(4.0), tvos(11.0)) = BNNSDataTypeUIntBit | 8,
;;   BNNSDataTypeUInt16           __API_AVAILABLE(macos(10.13), ios(11.0), watchos(4.0), tvos(11.0)) = BNNSDataTypeUIntBit | 16,
;;   BNNSDataTypeUInt32           __API_AVAILABLE(macos(10.13), ios(11.0), watchos(4.0), tvos(11.0)) = BNNSDataTypeUIntBit | 32,
;;   BNNSDataTypeUInt64           __API_AVAILABLE(macos(12.0), ios(15.0), watchos(8.0), tvos(15.0)) = BNNSDataTypeUIntBit | 64,

;;   BNNSDataTypeIndexedBit       __API_AVAILABLE(macos(10.12), ios(10.0), watchos(3.0), tvos(10.0)) = 0x80000,
;;   BNNSDataTypeIndexed1         __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeIndexedBit | 1,
;;   BNNSDataTypeIndexed2         __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeIndexedBit | 2,
;;   BNNSDataTypeIndexed4         __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeIndexedBit | 4,
;;   BNNSDataTypeIndexed8         __API_AVAILABLE(macos(10.12), ios(10.0), watchos(3.0), tvos(10.0)) = BNNSDataTypeIndexedBit | 8,

;;   BNNSDataTypeMiscellaneousBit __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = 0x100000,
;;   BNNSDataTypeBoolean          __API_AVAILABLE(macos(11.0), ios(14.0), watchos(7.0), tvos(14.0)) = BNNSDataTypeMiscellaneousBit | 8,
;; );


;; typedef struct {

;;   BNNSNDArrayFlags flags;
;;   BNNSDataLayout layout;

;;   size_t size[BNNS_MAX_TENSOR_DIMENSION];
;;   size_t stride[BNNS_MAX_TENSOR_DIMENSION];

;;   void * _Nullable data;
;;   BNNSDataType data_type;

;;   void * _Nullable table_data;
;;   BNNSDataType table_data_type;

;;   float data_scale; // 0.0f value will be set to 1.0f during computation
;;   float data_bias;

;; } BNNSNDArrayDescriptor;



(def bnns-datatypes
  {:float32 BNNSDataTypeFloat32})

(def NUM_TENSOR_DIMS 8)



;;Delayed so we can create a jar and have it dynamically configure when loaded
;;as opposed to at runtime.
(def array-desc-type* (delay (dt-struct/define-datatype! :bnns-array-desc
                               [{:name :flags :datatype :int32}
                                {:name :layout :datatype :int32}
                                {:name :size :datatype (dt-size-t/size-t-type) :n-elems NUM_TENSOR_DIMS}
                                {:name :stride :datatype (dt-size-t/size-t-type) :n-elems NUM_TENSOR_DIMS}
                                {:name :data :datatype (dt-size-t/size-t-type)}
                                {:name :data-type :datatype :int32}
                                {:name :table-data :datatype (dt-size-t/size-t-type)}
                                {:name :table-data-type :datatype :int32}
                                {:name :data-scale :datatype :float32}
                                {:name :data-bias :datatype :float32}])))




(dt-ffi/define-library!
  libaccelerate
  '{:BNNSCreateRandomGeneratorWithSeed {:rettype :pointer
                                        :argtypes [[method :int32] ;;just pass in zero
                                                   [seed :int64]
                                                   [filter-params :pointer?]]}
    :BNNSDestroyRandomGenerator {:rettype :void
                                 :argtypes [[generator :pointer]]}
    :BNNSRandomFillUniformFloat {:rettype :int32
                                 :argtypes [[generator :pointer]
                                            [array-desc :pointer]
                                            [a :float32]
                                            [b :float32]]}
    :BNNSRandomFillNormalFloat {:rettype :int32
                                :argtypes [[generator :pointer]
                                           [array-desc :pointer]
                                           [mean :float32]
                                           [stddev :float32]]}}
  nil
  nil)


(defn initialize!
  ([] (initialize! nil))
  ([options]
   (let [libpath (get options :accelerate-path "Accelerate")]
     (dt-ffi/library-singleton-set! libaccelerate libpath))))
