# 가져올 이미지를 정의
FROM node:20.9.0-alpine
# 작업 디렉토리 설정
WORKDIR /app

# Yarn Berry 설치
RUN corepack enable && corepack prepare yarn@stable --activate

# 종속성 파일 복사
COPY ./package.json ./yarn.lock ./
COPY /techpick/. ./techpick
COPY /techpick-shared/. ./techpick-shared
COPY /schema ./schema

# 종속성 설치
RUN yarn install
RUN yarn all install

# teckpick build전에 WORKDIR 변경
WORKDIR /app/techpick

# Build
RUN yarn run build

EXPOSE 3000

# 컨테이너 실행 시 실행될 명령 설정
CMD ["yarn", "start"]