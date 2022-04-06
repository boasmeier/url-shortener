#!/bin/bash
git checkout main
git pull origin main
docker-compose down
docker rmi $(docker image ls -aq)
docker-compose up --build -d
exit

