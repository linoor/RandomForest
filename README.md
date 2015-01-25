RandomForest
============

An implementation of the Random Forest algorithm on HOG images

### Set Up
Java 8 required.
#### Eclipse LUNA
1. Import project 
  1. *File > Import*, select *Git > Projects from Git*
  2. Configure project then *Finish*
2. Add JARs and libraries
  1. Right-click project *> Properties > Java Build Path*
  2. Select *Libraries > Add External JARs*, add `javadesigner.jar` (package from **Modelio**) and `Levis.jar` from file system
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
  1. Right-click folders `src` *> Mark Directory As > Sources Root*
  2. Right-click folders `tests` *> Mark Directory As > Test Sources Root*

### Usage
* Choose the test method of interest from `MainRun.java`
  *default setting: Train - digit images from DB; Test - selected 10, 20, 30 images from DB*
* Or  
  1. Add custom data sets to `assets` folder
  2. Create method for custom training in `MainRun.java` and set up file paths
  3. `runRandomForest`

### Reference
Random Forest
* [Random-Forest](https://github.com/ironmanMA/Random-Forest) (Java)

Histogram of Oriented Gradients
* [Histograms of Oriented Gradients for Human Detection](http://lear.inrialpes.fr/people/triggs/pubs/Dalal-cvpr05.pdf) (Navneet Dalal and Bill Triggs)
* [Hog-Processing](http://hogprocessing.altervista.org/) (Java)
* [HOG of skimage](http://scikit-image.org/docs/dev/auto_examples/plot_hog.html) (Python)
* [HOG tutorial] (https://chrisjmccormick.wordpress.com/2013/05/09/hog-person-detector-tutorial/)
* [Gradient Vector tutorial] (https://chrisjmccormick.wordpress.com/2013/05/07/gradient-vectors/)

Datase
* [Handwritten Digits](http://archive.ics.uci.edu/ml/datasets/Pen-Based+Recognition+of+Handwritten+Digits)
