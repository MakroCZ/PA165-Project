# Music manager application #
![CI status](https://img.shields.io/badge/build-passing-brightgreen.svg)

There is an open-sourced Java EE project which was created as a part of the subject Enterprise Java ([PA165](https://kore.fi.muni.cz/wiki/index.php/PA165)) at Faculty of Informatics, [Masaryk University](https://www.muni.cz/en). This project represents the web application for users who are interested in music culture. The application provides the access to the database of popular songs. The application provides REST API as well. The database contains information about song’s genres, albums and performers. Depending on user privileges the application allows to execute common CRUD operations, e.g. to help users view, search, and change information stored in the music manager application. 

## Table of wiki contexts
1. [ Homepage ](https://github.com/MakroCZ/PA165-Project/wiki)
1. [ Project description ](https://github.com/MakroCZ/PA165-Project/wiki/Project-description)
1. [ Installation guide ](https://github.com/MakroCZ/PA165-Project/wiki/Installation-guide)
1. [ Application usage ](https://github.com/MakroCZ/PA165-Project/wiki/Application-usage)
1. [ External tools ](https://github.com/MakroCZ/PA165-Project/wiki/External-tools)
1. [ Authors ](https://github.com/MakroCZ/PA165-Project/wiki#authors)
1. [ License ](https://github.com/MakroCZ/PA165-Project/wiki#license)

## Quick starting
The first step is to clone the git repository:
```
git clone https://github.com/MakroCZ/PA165-Project.git
```

Then build Java EE project with Maven:

```
mvn clean install
```
During the next step open server's directory:
```
cd MusicManager-MVC/
```
Finally, run Apache Tomcat server using command:
```
mvn
```

## Authors

* **Yehor Safonov** - *programming, wiki documentation, diagrams, frontend application*
* **Marek Bařinka** - *project structure, programming, authentication, git management*
* **Lukáš Suchánek** - *programming, testing, controllers, Apache Tomcat development*
* **Václav Stehlík** - *programming, REST API, documentation, testing*


## License

This project is licensed under the GNU LGPL 3.0 License - see the [LGPL License](https://www.gnu.org/licenses/lgpl-3.0.en.html) for more details.
