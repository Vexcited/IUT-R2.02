# R2.02

## Ressources

Voir le [répertoire `resources`](./resources/README.md).

## Comptes-rendus

Voir le [répertoire `writeups`](./writeups/README.md).

## Versions utilisées dans ce dépôt

> Ce sont les versions utilisées pour le développement de ce projet. Il est possible que des versions plus récentes ou anciennes fonctionnent également.

- Java `21.0.2`
- JavaFX `21.0.2`

## Exécuter le main d'une classe Java

### En utilisant `run.sh` (recommandé)

> Cette méthode ne fonctionne que pour les utilisateurs Linux et macOS.
> Pour les utilisateurs Windows, veuillez vous référer à la section suivante.

Vous devez d'abord donner les droits d'exécution au script `run.sh`, si ce n'est pas déjà fait.

```bash
chmod +x run.sh
```

Ensuite, vous devrez modifier la localisation de JavaFX (variable `JAVAFX_PATH` dans le script `run.sh` pour qu'elle corresponde à votre installation.

Par défaut elle est définie comme suit :

```bash
JAVAFX_PATH="/home/vexcited/.java-sdk-lib/javafx-21/lib"
```

Vous pouvez maintenant exécuter le script `run.sh` pour lancer l'application.

```bash
./run.sh tp1.exo1.TestJavaFX
```

Si tout se passe bien, vous devriez voir les messages suivants :

```console
Compilation et exécution de 'unilim.info.ihm.tp1.exo1.TestJavaFX' (avec JavaFX)

-> Chemin de la librairie JavaFX : /home/vexcited/.java-sdk-lib/javafx-21/lib
-> Chemin de la classe à exécuter : ./src/unilim/info/ihm/tp1/exo1/TestJavaFX.java

=> Compilation du projet avec 'javac' dans le dossier 'bin'
=> Exécution du projet avec 'java'
```

et une fenêtre JavaFX devrait s'ouvrir.

### En utilisant Visual Studio Code

Vous pouvez également exécuter le projet en utilisant Visual Studio Code grâce au fichier [`launch.json`](./.vscode/launch.json) fourni.

Cependant, vous devrez y apporter des modifications pour changer le chemin de JavaFX dans l'attribut `vmArgs` de la configuration.
`"vmArgs": "--module-path /home/vexcited/.java-sdk-lib/javafx-21/lib --add-modules javafx.controls"` devra être modifié pour correspondre à votre installation de JavaFX : `"vmArgs": "--module-path <path-to-javafx>/lib --add-modules javafx.controls"`

Il ne faut pas oublier de modifier aussi le fichier `settings.json` pour que JavaFX soit dans les librairies du projet.
Ainsi `"/home/vexcited/.java-sdk-lib/javafx-21/lib/**/*.jar"` deviendra `"<path-to-javafx>/lib/**/*.jar"`.

Vous pourrez ainsi démarrer et déboguer le projet en utilisant Visual Studio Code.

### En utilisant le CLI manuellement (sans `run.sh`)

Si vous ne pouvez pas utiliser le script `run.sh`, vous pouvez exécuter le projet manuellement en suivant les étapes suivantes.

#### 1. Compilation du projet

```bash
javac --module-path <path-to-javafx>/lib --add-modules javafx.controls -d bin src/unilim/info/ihm/tp1/exo1/TestJavaFX.java
```

#### 2. Exécution du projet

```bash
java --module-path <path-to-javafx>/lib --add-modules javafx.controls -cp bin unilim.info.ihm.tp1.exo1.TestJavaFX
```

#### 3. OK

Une fenêtre JavaFX devrait s'ouvrir.
