#Weekly report

Things done week 1

* Project creation
* Github repo and version control added to the project
* Settling on creating a compression/de-compression program
* Some research on different algorithms and settling on LZW
* Documenting stuff
* Labtool registration

Things done week 2

* Application structure
* Application library work with Java.Util.Zip package. My first couple of attempts using java.util.deflate/inflate resulted in "garbled" deflated files when the inputfile was over the buffer size. I just could not get the FileInputStream and FileOutputStream to create a deflated file that was identical to the inflated one. These never made it into the repo.
* Documentation

Things done week 3

* Own HashMap implementation added.
* Zipper compression added but not working as expected.
* Unit tests expanded.
* Documentation

Things done week 4

* Expanded on own HashMap implementation
* Fixed Zipper compression
* Zipper lzw decompression added but not working as expected. Figured it out. Changed to using a binary format for compressed output
* Added more tests
* Added a Timer class that might be enough for now
* Researching Huffman algorithm.
* Documentation

## Hours worked

| Date | Hours | What|
|---|---|---|
|2018-09-05| 2h | Project creation|
|2018-09-06| 3h | Research, settling on program and documenting|
|2018-09-09| 3h | Documentation touchup, Labtool registration, Research|
|2018-09-15| 6h | Application structure, library implementation for java.util.zip, a basic structure for gui/menu implementation, unit-tests|
|2018-09-16| 5h | Started on the Zippper implementation, research|
|2018-09-19| 4h | Expanded on the Zipper implementation, research|
|2018-09-20| 5h | Expanded on the Zipper implementation, added tests|
|2018-09-26| 4h | Expanded on own HashMap implementation, fixes to the Zipper compression, Zipper decompression work started, added a few tests|
|2018-09-27| 4h | Research why the decompression is failing, added more tests, started work on the Timer class |
|2018-09-28| 6h | Timer class changes, added more tests and trying to figure out the Huffman coding, updated the Zipper implementation to use more proper binary encoding|
