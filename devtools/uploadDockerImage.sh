#!/bin/bash

# Before this works, you need to login to docker with `docker login`
docker buildx build --platform linux/amd64,linux/arm64 -t rjck/drink-smart-backend:dev --push .
