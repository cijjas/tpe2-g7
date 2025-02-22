#!/bin/bash

CLIENT_JARS="../../client/target/tpe2-g7-client-2024.1Q/lib/jars/*"
TARGET_CLIENT="ar.edu.itba.pod.tpe2.client.query2.Query2Client"
TARGET_DIR="../../client/target/tpe2-g7-client-2024.1Q"

if [ ! -d "$TARGET_DIR" ]; then
    tar -xzf "../../client/target/tpe2-g7-client-2024.1Q-bin.tar.gz" -C "../../client/target/"
fi

java -Dhazelcast.logging.type=none -cp "$CLIENT_JARS" "$TARGET_CLIENT" "$@"
