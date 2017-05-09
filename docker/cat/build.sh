
workDir=`pwd`
echo $workDir
gitCatPath=/Users/hg/Gitopen/cat
cd $gitCatPath

# gen cat-home.war
# mvn clean install -DskipTests

# gen sql xml config file
#mvn cat:install

cp $gitCatPath/script/Cat.sql $workDir/cat
cp $gitCatPath/cat-home/target/*.war $workDir/cat/cat.war
