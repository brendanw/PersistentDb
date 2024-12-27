package com.basebeta.wasmdb

import app.cash.sqldelight.async.coroutines.awaitAsOne
import com.basebeta.kdb.BooleanDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AppModel {
   val showContentFlow = MutableStateFlow(false)
   lateinit var db: BooleanDatabase

   init {
      val driver = getDbDriver()
      db = BooleanDatabase(driver)

      GlobalScope.launch(Dispatchers.Default) {
         val hasContent = db.showContentEntityQueries.hasContent().awaitAsOne()
         showContentFlow.emit(hasContent)
      }
   }

   fun updateBoolean(flag: Boolean) {
      GlobalScope.launch(Dispatchers.Default) {
         showContentFlow.emit(flag)
         db.showContentEntityQueries.updateHasContent(flag)
      }
   }
}