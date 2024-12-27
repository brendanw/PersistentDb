package com.basebeta.wasmdb

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker

actual fun getDbDriver(): SqlDriver {
   return WebWorkerDriver(jsWorker())
}

internal fun jsWorker(): Worker =
   js("""new URL("sqldelight-sqlite-wasm-worker/sqlitewasm.worker.js", import.meta.url)""")