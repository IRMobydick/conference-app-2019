package io.github.droidkaigi.confsched2019.session.ui.actioncreator

import io.github.droidkaigi.confsched2019.action.Action
import io.github.droidkaigi.confsched2019.di.PageScope
import io.github.droidkaigi.confsched2019.dispatcher.Dispatcher
import io.github.droidkaigi.confsched2019.model.SearchResult
import io.github.droidkaigi.confsched2019.model.SessionContents
import io.github.droidkaigi.confsched2019.system.actioncreator.ErrorHandler
import io.github.droidkaigi.confsched2019.util.ScreenLifecycle
import io.github.droidkaigi.confsched2019.util.coroutineScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@PageScope
class SearchActionCreator @Inject constructor(
    override val dispatcher: Dispatcher,
    private val screenLifecycle: ScreenLifecycle
) : CoroutineScope by screenLifecycle.coroutineScope,
    ErrorHandler {
    fun search(query: String?, sessionContents: SessionContents) {
        // if we do not have query, we should show speakers and sessions
        if (query.isNullOrBlank()) {
            dispatcher.launchAndDispatch(
                Action.SearchResultLoaded(
                    SearchResult(
                        sessionContents.sessions,
                        sessionContents.speakers,
                        query
                    )
                )
            )
            return
        }
        val searchResult = sessionContents.search(query)
        dispatcher.launchAndDispatch(Action.SearchResultLoaded(searchResult))
    }
}
