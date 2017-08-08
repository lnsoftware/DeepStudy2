REPO=huiwq1990/thrift

.DEFAULT: build
.PHONY: build push all

all: build

build:
	docker build -t $(REPO):0.8.0 .
push:
	docker push $(REPO)

run:
	docker run -t -rm $(REPO) $(filter-out $@,$(MAKECMDGOALS)) $(ARGS)

bash:
	docker run -t -i -rm  $(REPO) /bin/bash
