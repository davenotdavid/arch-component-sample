package com.davenotdavid.archcomponentsample.model

/**
 * TODO: State object as part of the Model layer
 * TODO: Action to reduce immutably such as a button to refresh data
 * TODO: Naming
 */
sealed class HeadlineIntent {

    object HeadlineList : HeadlineIntent()
    object HeadlineDetails : HeadlineIntent()

}
