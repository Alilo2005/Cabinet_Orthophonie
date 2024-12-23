# CONTRIBUTION

# Contributions to project
2024

First of all, thank you for willing to contribute in making this
stuff a memorable one.

## Quick start

**All these steps are done only once**

1. creat the directory of the project:

```bash
mkdir Cabinet_Orthophonie
cd Cabinet_Orthophonie
```

1. Clone the repository:

```bash
git clone https://github.com/Alilo2005/Cabinet_Orthophonie.git
```

1. Copy the .git file of the repository to the working directory ( test that by running git status )

```bash
mv Cabinet_Orthophonie/* ./
mv Cabinet_Orthophonie/.git ./
rm -rf Cabinet_Orthophonie
# test that all is okay
git status
```

1. Configure remote repository:

```bash
git remote add origin https://github.com/Alilo2005/Cabinet_Orthophonie.git
```

**The next steps are done every time you start working** 

1. Make a separate branch or change to an existing branch:

```bash
# in case new branch
git checkout -b {which side}-{functionality} ( ex: git checkout -b server-pinValidation )
# in case existing branch
git checkout {your branch name}
```

1. Make sure to fetch from origin first :

```bash
git pull origin main
```

### Important note

**Always** pull from origin before starting to make
your local changes :

1. Make changes locally.
2. Add the affected files :

### NOTE:

Don’t add any binaries or other libraries files, only source files ( code.java … )

```bash
git add Cabinet_Orthophonie/java/code.java
```

1. Commit your changes :

```bash
git commit -m "<any message to explain what changes you made"
```

1. Push your changes :

### NOTE:

DO NOTE PUSH TO THE MAIN BRANCH, NEVER!

```bash
git push origin {your branch name}
```

1. Make a pull request on Github.
