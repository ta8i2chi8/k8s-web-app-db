## 環境構築
1. DBサーバ構築
（mysql-hostpath-pv.yamlのtype: DirectoryOrCreateがコメントアウトされているので、ディレクトリがない場合はデータの永続化がされない。）
```
kubectl apply -f mysql-hostpath-pv.yaml -f mysql-deployment.yaml -f mysql-secret.yaml
```
2. デプロイ状況確認
```
watch -t 'kubectl get pod,deploy,svc,secret -n db -o wide'
```
3. DBにデータ作成（以下をしておかないと、springboot起動時にエラーが出る。）
→dockerfileに記述したい
* kubectl run -it --rm --image=mysql:8.3 --restart=Never --namespace=db mysql-client -- mysql -h mysql-service -u root -prootpass
* databaseを作成しておく CREATE DATABASE sampledb;
* tableを作成しておく CREATE TABLE sampledb.hello (message varchar(100));
* データを入れておく INSERT INTO sampledb.hello VALUE('hello db!');
* mysqlのユーザーに作成したdbへのアクセス権を与えておく GRANT SELECT,INSERT,UPDATE,DELETE ON sampledb.* TO 'mysqluser'@'%';
4. アプリサーバ構築
```
kubectl apply -f springboot-deployment.yaml
```
5. デプロイ状況確認
```
watch -t 'kubectl get pod,deploy,svc,secret -n app -o wide'
```
6. クラスター内からcurlでAPIを動作確認
```
kubectl run --restart Never --image curlimages/curl:7.68.0 -it --rm curl sh
```
```
curl http://springboot-service.app:8080/api/hello-world-db
```
7. ウェブサーバ構築
```
kubectl apply -f nginx-deployment.yaml
```
8. デプロイ状況確認
```
watch -t 'kubectl get pod,deploy,svc,secret -n web -o wide'
```
9. クラスター内からcurlでUIを動作確認
```
kubectl run --restart Never --image curlimages/curl:7.68.0 -it --rm curl sh
```
```
curl http://nginx-service.web:80
```
```
kubectl port-forward service/nginx-service 8080:80
```
```
curl http://localhost:8080
```


## 削除手順
kubectl delete -f nginx-deployment.yaml
kubectl delete -f springboot-deployment.yaml
kubectl delete -f mysql-hostpath-pv.yaml -f mysql-deployment.yaml -f mysql-secret.yaml


## インフラ構成図
![web-app-db drawio](https://github.com/ta8i2chi8/k8s-web-app-db/assets/66934929/c32c60d1-7bfb-4728-9e53-335c69507f6c)


