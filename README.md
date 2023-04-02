# AppleMath

Dynamic bindings to apple's accelerate framework.  This project started
as a research project into apple's blas system but they have since
deprecated all of their blas bindings - I think openblas now takes
precedence.


In any case, there is still significant functionality in accelerate 
beside blas and lapack.  So far I have only explored random number
generation using their bnns system.

For an example see the stream library's [perftest](https://github.com/cnuernber/streams/blob/master/dev/src/perftest.clj#L35).
