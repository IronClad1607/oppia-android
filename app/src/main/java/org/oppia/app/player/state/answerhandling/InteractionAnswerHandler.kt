package org.oppia.app.player.state.answerhandling

import androidx.databinding.ObservableField
import org.oppia.app.model.UserAnswer

/**
 * A handler for interaction answers. Handlers can either require an additional user action before the answer can be
 * processed, or they can push the answer directly to a  [InteractionAnswerReceiver]. Implementations must indicate
 * whether they require an explicit submit button.
 */
interface InteractionAnswerHandler {
  /**
   * Returns whether this handler requires explicit answer submission. Note that this is expected to be an invariant for
   * the lifetime of this handler instance.
   */
  fun isExplicitAnswerSubmissionRequired(): Boolean = true

  fun onAnswerRealTimeError(errorMessage: ObservableField<String>, pendingAnswerError: String?) {}

  /**
   * Returns the flag that indicates current answer has error message or not on Submit button click.
   * If [setPendingAnswerError] is null then returns true else false.
   */
  fun hasPendingAnswerErrorOnSubmit(): Boolean = false

  /** Return the current answer's error messages  if not valid else return null. */
  fun getPendingAnswerError(): String? {
    return null
  }

  /** Return the current answer that is ready for handling. */
  fun getPendingAnswer(): UserAnswer? {
    return null
  }
}

/**
 * A callback that will be called by [InteractionAnswerHandler]s when a user submits an answer. To be implemented by
 * the parent fragment of the handler.
 */
interface InteractionAnswerReceiver {
  fun onAnswerReadyForSubmission(answer: UserAnswer)
}

/** Categories of errors that can be inferred from a pending answer.  */
enum class AnswerErrorCategory {
  /** Corresponds to errors that may be found while the user is trying to input an answer.  */
  REAL_TIME,
  /** Corresponds to errors that may be found only when a user tries to submit an answer.  */
  SUBMIT_TIME
}
