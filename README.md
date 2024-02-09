
# STAR DEC
[![Docker Pulls](https://img.shields.io/docker/pulls/bradleypelton/starfirst)](https://hub.docker.com/repository/docker/bradleypelton/starfirst/general)
[![Docker Image Size](https://img.shields.io/docker/image-size/bradleypelton/starfirst?sort=date)](https://hub.docker.com/repository/docker/bradleypelton/starfirst/general)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)


### ABOUT
Spring boot app to explore celestial objects and celestial bodies. Data persistence with JPA/Hibernate, search functionality with Elasticsearch

### Features
- Full-text search with ElasticSearch
- Containerized (see docker-compose.yml)
- Real-time indexing of new data, persistence with Postgres database
- Login/JWT Authentication with Spring Security

### Technologies
- Spring boot
- Spring web for external API
- Spring data & Hibernate for data persistence
- Spring Security
- Database = Postgres 13
- Elasticsearch for indexing/searching  (v7.13.2)
- Frontend = basic Thymeleaf(server-side java template engine) + boostrap
- Junit unittests
- Maven
- Cloud provider: AWS

### TODO LIST
- See TODO.txt

### DATA
- Open source data from the Saguaro Astronomy Club. https://www.saguaroastro.org/sac-downloads/

### Name
- It was originally a play on words "stare decisis"(in law, 'determining points in litigation according to precedent.')
- Stare(latin, pronounce stehr) -> starry (star-ie, meaning multiple stars. See : "The Starry Night" by Vincent van Gogh)
- starry decisis -> star dec
- star dec -> star Deck

### Useful links for later
https://www.convertcsv.com/csv-to-sql.htm