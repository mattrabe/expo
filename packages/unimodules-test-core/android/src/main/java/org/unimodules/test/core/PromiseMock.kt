package org.unimodules.test.core

import org.unimodules.core.Promise

enum class PromiseState {
  NONE,
  REJECTED,
  RESOLVED,
  ILLEGAL
}

class PromiseMock : Promise {

  var state = PromiseState.NONE
  var resolvedValue: Any? = null

  var resolveValueSet: Boolean = false
  var resolveValue: Any? = null
    set(value) {
      this.resolveValueSet = true
      field = value
    }

  var rejectCodeSet: Boolean = false
  var rejectCode: String? = null
    set(value) {
      this.rejectCodeSet = true
      field = value
    }

  var rejectMessageSet: Boolean = false
  var rejectMessage: String? = null
    set(value) {
      this.rejectMessageSet = true
      field = value
    }

  var rejectThrowableSet: Boolean = false
  var rejectThrowable: Throwable? = null
    set(value) {
      this.rejectCodeSet = true
      field = value
    }


  override fun resolve(value: Any?) {
    assertNotResolvedNorRejected()
    state = PromiseState.RESOLVED
    resolveValue = value
  }

  override fun reject(code: String?, message: String?, e: Throwable?) {
    assertNotResolvedNorRejected()
    state = PromiseState.REJECTED
    rejectCode = code
    rejectMessage = message
    rejectThrowable = e
  }

  override fun reject(e: Throwable?) {
    assertNotResolvedNorRejected()
    state = PromiseState.REJECTED
    rejectThrowable = e
  }

  override fun reject(code: String?, message: String?) {
    assertNotResolvedNorRejected()
    state = PromiseState.REJECTED
    rejectCode = code
    rejectMessage = message
  }

  override fun reject(code: String?, e: Throwable?) {
    assertNotResolvedNorRejected()
    state = PromiseState.REJECTED
    rejectCode = code
    rejectThrowable = e
  }

  private fun assertNotResolvedNorRejected() {
    when (state) {
      PromiseState.RESOLVED, PromiseState.REJECTED, PromiseState.ILLEGAL -> {
        state = PromiseState.ILLEGAL
        throw IllegalStateException("Cannot resolve same promise twice!")
      }
      else -> {
      }
    }
  }
}