docker-build:
	docker build --tag clojure-dev-env .

docker-run:
	docker run -it --rm --name fwpd -v $(PWD):/app clojure-dev-env:latest

docker-exec-bash:
	docker exec -it fwpd bash