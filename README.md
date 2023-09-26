# Gestor Universitario

Proyecto final para la materia Programación y Diseño Orientado a Objetos

## Instalar java-17

```bash
$ # Para instalar la versión community en Linux(ubuntu)
$ sudo apt install openjdk-17-jdk
$ # Listar versiones disponibles
$ sudo update-alternatives --list java
$ # Configurar la versión de java en caso de tener muchas instaladas
$ sudo update-alternatives --config java
$ # Controlar version
$ java -version
```

### Configurar JAVA_HOME.

En el archivo **~/.bashrc** agregar las siguientes lineas:

```text
# JAVAHOME
export JAVA_HOME=$(which java)
```

## IDE intellij community

```bash
$ # Para instalar la versión community en Linux(ubuntu)
$ sudo snap install intellij-idea-community --classic
```
