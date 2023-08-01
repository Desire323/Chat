FROM arm64v8/cassandra:3.11.2

ENV LANG C.UTF-8

COPY *.cql /docker-entrypoint-initdb.d/

COPY entrypoint.sh /

RUN ["chmod", "+x", "/entrypoint.sh"]

ENTRYPOINT ["/entrypoint.sh"]

CMD ["cassandra", "-f"]