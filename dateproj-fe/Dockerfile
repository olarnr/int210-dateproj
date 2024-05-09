FROM node:lts-alpine AS build
WORKDIR code
COPY package*.json vite.config.js index.html ./
RUN npm install
COPY src/ src/

RUN npm run build

FROM nginx:alpine AS run
COPY --from=build /code/dist/ /dist/
COPY default.conf /etc/nginx/conf.d/default.conf
