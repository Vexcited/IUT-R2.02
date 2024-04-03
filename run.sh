#!/bin/bash

# Vérifier le nombre d'arguments
if [ "$#" -ne 1 ]; then
  echo "Exemple d'utilisation: ./run.sh tp1.exo1.TestJavaFX"
  exit 1
fi

# À modifier si vous avez installé JavaFX ailleurs !
JAVAFX_PATH="/home/vexcited/.java-sdk-lib/javafx-21/lib"

# On vérifie si le chemin existe avec `javafx.controls.jar`.
if [ ! -f "$JAVAFX_PATH/javafx.controls.jar" ]; then
  echo -e "=> Le chemin de JavaFX est incorrect : '$JAVAFX_PATH'\nVeuillez le modifier dans le fichier 'run.sh'."
  exit 2
fi

# Les modules de JavaFX qu'on va utiliser.
JAVAFX_MODULES="javafx.controls"

# La classe sans la partie `unilim.info.ihm`
# Exemple : pour exécuter `unilim.info.ihm.tp1.exo1.TestJavaFX`, on va donner `tp1.exo1.TestJavaFX`
SUB_CLASS=$1
FULL_CLASS="unilim.info.ihm.$SUB_CLASS"

# On doit remplacer les `.` par des `/` pour que le chemin soit correct.
# Exemple : `tp1.exo1.TestJavaFX` devient `tp1/exo1/TestJavaFX`
SUB_PATH=$(echo $SUB_CLASS | tr '.' '/')
FULL_PATH="unilim/info/ihm/$SUB_PATH.java"

# On vérifie que la classe existe.
if [ ! -f "src/$FULL_PATH" ]; then
  echo -e "=> La classe '$FULL_CLASS' n'existe pas.\nVeuillez vérifier le nom de la classe."
  exit 3
fi

clear
echo "Compilation et exécution de '$FULL_CLASS' (avec JavaFX)"
echo ""
echo "-> Chemin de la librairie JavaFX : $JAVAFX_PATH"
echo "-> Chemin de la classe à exécuter : ./src/$FULL_PATH"
echo ""

echo "=> Compilation du projet avec 'javac' dans le dossier 'bin'"
rm -rf bin && javac --module-path $JAVAFX_PATH --add-modules $JAVAFX_MODULES -d bin src/$FULL_PATH

# On vérifie si la compilation a réussi.
if [ $? -ne 0 ]; then
  echo "=> La compilation a échoué, l'exécution est impossible."
  exit 4
fi

echo "=> Exécution du projet avec 'java'"
java --module-path $JAVAFX_PATH --add-modules $JAVAFX_MODULES -cp bin $FULL_CLASS
