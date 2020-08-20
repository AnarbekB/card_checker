#!/bin/sh
mvn dependency:resolve
mvn clean compile assembly:single
mvn test
