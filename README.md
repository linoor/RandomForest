RandomForest
============

An implementation of the Random Forest algorithm on HOG images

## Set Up
Java 8 required.
#### Eclipse LUNA
1. Import project
  1. *File > Import*, select *Git > Projects from Git*
  2. Configure project then *Finish*
2. Add JARs and libraries
  1. Right-click project *> Properties > Java Build Path*
  2. Select *Libraries > Add External JARs*, add `javadesigner.jar` (package from **Modelio**) and `Levis.jar` from file system (or `libs`)
  3. Add [hamcrest](http://search.maven.org/#search|ga|1|g%3Aorg.hamcrest) JAR as well
  3. Select *Libraries > Add Library*, add `JUnit 4` library
  4. Check all added libraries in *Order and Export*
3. Configure source folders
  1. Right-click folders `src` and `tests` *> Build Path > Use as Source Folder*

#### IntelliJ IDEA
1. Import project
  1. Choose *Checkout from Version Control > Git*
  2. Configure project to clone
2. Add JARs and libraries
  1. Right-click project *> Open Module Settings*, select *Modules* panel
  2. In *Dependencies* tab, add `javadesigner.jar` `Levis.jar` and libraries for `JUnit` and `hamcrest`
3. Configure source folders
  1. Right-click folder `src` *> Mark Directory As > Sources Root*
  2. Right-click folder `tests` *> Mark Directory As > Test Sources Root*

## Usage
* Choose the test method of interest from `MainRun.java`  
  *default setting: Train - digit images from DB; Test - selected 10, 20, 30 images from DB*
* Or
  1. Add custom data sets to `assets` folder
  2. Create method for custom training in `MainRun.java` and set up file paths
  3. `runRandomForest`
* Tune parameters
  1. `numOfTree` - number of tree in forest
  2. `depthOfTree` - maximum depth of each tree  

> To set the bootstrap rate of training/testing data or attributes, add parameters when calling the constructor of `RandomForestLearner`.

## Unit Test
Uses **JUnit** and **hamcrest**.
#### Eclipse
* Right-click folder `tests` *> Run As > 3. JUnit Test*

#### IntelliJ
* Right-click folder `tests` *> Run 'All Tests'*

## General Test
1. Test on small patch of DB images
  * Run `runHOGSmallPatch` in `main`
  * Train random forest model via a small subset of data: 10, 20, 30
2. Tet on DB images
  * Run `runHOGDB` in `main`
  * Train random forest model via the whole DB, add own test cases in `assets/Test/testset`
3. Test on handwritten digits (refer to Reference)
  * Run `runTestPendigits` in `main`
  * Train random forest model with handwritten digits

## Reference
Random Forest
* [Random-Forest](https://github.com/ironmanMA/Random-Forest) (Java)

Histogram of Oriented Gradients
* [Histograms of Oriented Gradients for Human Detection](http://lear.inrialpes.fr/people/triggs/pubs/Dalal-cvpr05.pdf) (Navneet Dalal and Bill Triggs)
* [Hog-Processing](http://hogprocessing.altervista.org/) (Java)
* [HOG tutorial] (https://chrisjmccormick.wordpress.com/2013/05/09/hog-person-detector-tutorial/)
* [Gradient Vector tutorial] (https://chrisjmccormick.wordpress.com/2013/05/07/gradient-vectors/)

Dataset
* [Handwritten Digits](http://archive.ics.uci.edu/ml/datasets/Pen-Based+Recognition+of+Handwritten+Digits)
