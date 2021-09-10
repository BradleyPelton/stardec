

# SEE THE (Sometimes) LIVE SITE
- http://starrydecisis.com
- http://starrydecisis.com
- http://starrydecisis.com
- http://starrydecisis.com
- http://starrydecisis.com

- 
### NOTE
- Elasticsearch likes to idle above 8Gb of ram, so I'm still messing around with AWS configurations.
- The site most likely will be up at any given time, but 100% uptime won't be achieved for some time.


# ABOUT
Spring boot app to explore celestial objects and celestial bodies. Data persistence with JPA/Hibernate, search functionality with Elasticsearch

### FRONTEND DISCLAIMER:
- This is a "backend" project meant to show off advanced backend features like advanced relevancy searching.
- The frontend is added for observers who can't be bothered to read the API contract.
- The frontend is meant to be entirely simplistic and not meant to display HTML/CSS/Javascript prowess

### Technologies
- Spring boot
- Spring web for external API
- Spring data & Hibernate for data persistence
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