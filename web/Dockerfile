# ビルドステージ
FROM node:18.19.0-alpine3.18 as build-stage
WORKDIR /app
COPY . .
RUN yarn install
RUN npx nuxi generate


# 実行ステージ
FROM nginx:1.25
WORKDIR /app

ENV API_SERVER_URL "springboot-service.app.svc.cluster.local"
ENV API_SERVER_PORT "8080"

# /etc/nginx/conf.d/default.confファイルの作成
RUN mkdir -p /etc/nginx/conf.d \
    && chmod 755 /etc/nginx/conf.d \
    && chown nginx /etc/nginx/conf.d
RUN touch /etc/nginx/conf.d/default.conf \
    && chmod 755 /etc/nginx/conf.d/default.conf \
    && chown nginx /etc/nginx/conf.d/default.conf

# ビルドステージから必要なファイルのみをコピー
COPY ./nginx_conf/nginx.conf /etc/nginx/nginx.conf
COPY ./nginx_conf/server.conf /etc/nginx/conf.d/default.conf.template
COPY --from=build-stage /app/.output/public/ /usr/share/nginx/html/

# 環境変数をdefault.confに展開
RUN envsubst '$$API_SERVER_URL, $$API_SERVER_PORT' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf

# nginxユーザーでも起動できるようにファイル権限を修正
RUN chown -R 101:101 /var/cache/nginx \
    && chmod -R g+w /var/cache/nginx \
    && chown -R 101:101 /var/run/ \
    && chmod -R g+w /var/run/

USER nginx
EXPOSE 29999

CMD ["nginx", "-g", "daemon off;"]