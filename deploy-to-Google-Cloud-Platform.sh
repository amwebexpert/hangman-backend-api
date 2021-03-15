clear
echo This will publish the new webapp on GAE
read \?"I am waiting for you to press [Enter] before I continue."

echo Building project
./gradlew --version
./gradlew clean build

echo Deploying the webapp
read \?"I am waiting for you to press [Enter] before I continue."
gcloud config list project
gcloud app describe
./gradlew appengineDeploy

echo Starting browser at welcome screen
gcloud app browse

echo END.
