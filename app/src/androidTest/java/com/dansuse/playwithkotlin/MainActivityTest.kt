package com.dansuse.playwithkotlin

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.dansuse.playwithkotlin.model.Event
import com.dansuse.playwithkotlin.model.League
import com.dansuse.playwithkotlin.presenter.DetailPresenter
import com.dansuse.playwithkotlin.presenter.MainPresenter
import com.dansuse.playwithkotlin.view.DetailView
import com.dansuse.playwithkotlin.view.MainView
import com.dansuse.playwithkotlin.view.activity.DetailActivity
import com.dansuse.playwithkotlin.view.activity.MainActivity
import com.dansuse.playwithkotlin.view.fragment.MatchesFragment
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val mainPresenter = Mockito.mock(MainPresenter::class.java)
    private val detailPresenter = Mockito.mock(DetailPresenter::class.java)

    @Rule
    @JvmField var mainActivityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java){
//        override fun afterActivityLaunched() {
//            //super.afterActivityLaunched()
//            val matchesFragment = this.activity.supportFragmentManager.findFragmentById(R.id.main_container) as MatchesFragment
//            MatchesFragment.presenter = presenter
//        }
        override fun beforeActivityLaunched() {
            MatchesFragment.presenter = mainPresenter
        }
    }

    @Rule
    @JvmField var detailActivityRule =
            object : ActivityTestRule<DetailActivity>(DetailActivity::class.java, true, false){
//                override fun getActivityIntent(): Intent {
//                    val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
//                    return Intent(targetContext, DetailActivity::class.java).apply {
//                        putExtra("event", "576548")
//                    }
//                }

                override fun beforeActivityLaunched() {
                    DetailActivity.presenter = detailPresenter
                }
            }

    @Before
    fun setUp(){
        InstrumentationRegistry.getTargetContext().deleteDatabase("FavoriteEvent.db")
    }

    @Test
    fun testRecyclerViewBehaviour() {
        val events = listOf<Event>(
                Event(
                    id = "576548",
                        date = "07/10/18",
                        homeScore = "1",
                        awayScore = "5",
                        homeTeamName = "Fulham",
                        awayTeamName = "Arsenal",
                        homeFormation = "",
                        awayFormation = "",
                        homeGoalDetails = "44':Andre Schuerrle;",
                        awayGoalDetails = "29':Alexandre Lacazette;49':Alexandre Lacazette;68':Aaron Ramsey;79':Pierre-Emerick Aubameyang;90':Pierre-Emerick Aubameyang;",
                    homeShots = null,
                        awayShots = null,
                    homeGoalKeeper = "Marcus Bettinelli; ",
                        awayGoalKeeper = "Bernd Leno; ",
                        homeLineupDefense = "Denis Odoi; Tim Ream; Alfie Mawson; Maxime Le Marchand; ",
                        homeLineupMidfield = "Tom Cairney; Jean Michael Seri; Andre Schuerrle; Luciano Dario Vietto; Ryan Sessegnon; ",
                        homeLineupForward = "Aleksandar Mitrovic; ",
                        homeLineupSubstitutes = "Sergio Rico; Alfie Mawson; Kevin McDonald; Stefan Johansen; Steven Sessegnon; Floyd Ayite; Aboubakar Kamara; ",
                        homeTeamId = "133600",
                        awayLineupDefense = "Hector Bellerin; Shkodran Mustafi; Sokratis Papastathopoulos; Nacho Monreal; ",
                        awayLineupMidfield = "Lucas Torreira; Granit Xhaka; Mesut Oezil; Aaron Ramsey; Pierre-Emerick Aubameyang; ",
                        awayLineupForward = "Alexandre Lacazette; ",
                        awayLineupSubstitutes = "Emiliano Martinez; Sokratis Papastathopoulos; Stephan Lichtsteiner; Sead Kolasinac; Aaron Ramsey; Matteo Guendouzi; Pierre-Emerick Aubameyang; ",
                        awayTeamId = "133604",
                        awayBadge = "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png",
                        homeBadge = "https://www.thesportsdb.com/images/media/team/badge/xwwvyt1448811086.png"
                ),
                Event(
                        id = "576543",
                        date = "07/10/18",
                        homeScore = "1",
                        awayScore = "5",
                        homeTeamName = "HAHAHA",
                        awayTeamName = "HEHEHE",
                        homeFormation = "",
                        awayFormation = "",
                        homeGoalDetails = "44':Andre Schuerrle;",
                        awayGoalDetails = "29':Alexandre Lacazette;49':Alexandre Lacazette;68':Aaron Ramsey;79':Pierre-Emerick Aubameyang;90':Pierre-Emerick Aubameyang;",
                        homeShots = null,
                        awayShots = null,
                        homeGoalKeeper = "Marcus Bettinelli; ",
                        awayGoalKeeper = "Bernd Leno; ",
                        homeLineupDefense = "Denis Odoi; Tim Ream; Alfie Mawson; Maxime Le Marchand; ",
                        homeLineupMidfield = "Tom Cairney; Jean Michael Seri; Andre Schuerrle; Luciano Dario Vietto; Ryan Sessegnon; ",
                        homeLineupForward = "Aleksandar Mitrovic; ",
                        homeLineupSubstitutes = "Sergio Rico; Alfie Mawson; Kevin McDonald; Stefan Johansen; Steven Sessegnon; Floyd Ayite; Aboubakar Kamara; ",
                        homeTeamId = "133600",
                        awayLineupDefense = "Hector Bellerin; Shkodran Mustafi; Sokratis Papastathopoulos; Nacho Monreal; ",
                        awayLineupMidfield = "Lucas Torreira; Granit Xhaka; Mesut Oezil; Aaron Ramsey; Pierre-Emerick Aubameyang; ",
                        awayLineupForward = "Alexandre Lacazette; ",
                        awayLineupSubstitutes = "Emiliano Martinez; Sokratis Papastathopoulos; Stephan Lichtsteiner; Sead Kolasinac; Aaron Ramsey; Matteo Guendouzi; Pierre-Emerick Aubameyang; ",
                        awayTeamId = "133604",
                        awayBadge = "https://www.thesportsdb.com/images/media/team/badge/vrtrtp1448813175.png",
                        homeBadge = "https://www.thesportsdb.com/images/media/team/badge/xwwvyt1448811086.png"
                )
        )
        val leagues = listOf<League>(
                League(
                        id = "4328",
                        leagueName = "English Premier League"
                ),
                League(
                        id = "4329",
                        leagueName = "English League Championship"
                )
        )
//        `when`(mainPresenter.getLeagueList())
//                .then {
//                    mainActivityRule.activity.runOnUiThread {
//                        EspressoIdlingResource.mCountingIdlingResource.increment()
//                        val matchesFragment:MatchesFragment = mainActivityRule.activity.supportFragmentManager.findFragmentById(R.id.main_container) as MatchesFragment
//                        matchesFragment.showLeagueList(leagues)
//                        EspressoIdlingResource.mCountingIdlingResource.decrement()
//                    }
//                }
//
//
//
//        `when`(mainPresenter.get15EventsByLeagueId(
//                ArgumentMatchers.anyString(), ArgumentMatchers.anyBoolean()))
//                .then {
//                    mainActivityRule.activity.runOnUiThread {
//                        EspressoIdlingResource.mCountingIdlingResource.increment()
//                        val matchesFragment:MatchesFragment = mainActivityRule.activity.supportFragmentManager.findFragmentById(R.id.main_container) as MatchesFragment
//                        matchesFragment.showEventList(events)
//                        EspressoIdlingResource.mCountingIdlingResource.decrement()
//                    }
//                }
//
//        `when`(detailPresenter.getEventDetailById(
//                ArgumentMatchers.anyString()))
//                .then {
//                    detailActivityRule.activity.runOnUiThread {
//                        EspressoIdlingResource.mCountingIdlingResource.increment()
//                        detailActivityRule.activity.showEventDetail(events[1])
//                        EspressoIdlingResource.mCountingIdlingResource.decrement()
//                    }
//                }



        //val matchesFragment:MatchesFragment = mainActivityRule.activity.supportFragmentManager.findFragmentById(R.id.main_container) as MatchesFragment
        //IdlingRegistry.getInstance().register(matchesFragment.getIdlingResourceInTest())
        IdlingRegistry.getInstance().register(EspressoIdlingResource.mCountingIdlingResource)

        mainActivityRule.activity.runOnUiThread {
            EspressoIdlingResource.mCountingIdlingResource.increment()
            val matchesFragment:MatchesFragment = mainActivityRule.activity.supportFragmentManager.findFragmentById(R.id.main_container) as MatchesFragment
            matchesFragment.showLeagueList(leagues)
            EspressoIdlingResource.mCountingIdlingResource.decrement()
        }

        mainActivityRule.activity.runOnUiThread {
            EspressoIdlingResource.mCountingIdlingResource.increment()
            val matchesFragment:MatchesFragment = mainActivityRule.activity.supportFragmentManager.findFragmentById(R.id.main_container) as MatchesFragment
            matchesFragment.showEventList(events)
            EspressoIdlingResource.mCountingIdlingResource.decrement()
        }

        onView(withId(R.id.list_event))
                .check(matches(isDisplayed()))
        verify(mainPresenter, times(1)).getLeagueList()
        events.forEach {
            onView(withText(it.homeTeamName)).check(matches(isDisplayed()))
            onView(withText(it.awayTeamName)).check(matches(isDisplayed()))
        }
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        onView(withId(R.id.list_event)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        detailActivityRule.activity.runOnUiThread {
            EspressoIdlingResource.mCountingIdlingResource.increment()
            detailActivityRule.activity.showEventDetail(events[1])
            EspressoIdlingResource.mCountingIdlingResource.decrement()
        }
//
//        //detailActivityRule.
//        //IdlingRegistry.getInstance().register(detailActivityRule.activity.getIdlingResourceInTest())
//        onView(withId(R.id.add_to_favorite))
//                .check(matches(isDisplayed()))
//        onView(withId(R.id.add_to_favorite)).perform(click())
//        onView(withText("Added to favorite"))
//                .check(matches(isDisplayed()))
//        onView(withText("Event is still loading"))
//                .check(matches(isDisplayed()))
//        pressBack()
    }

//    @Test
//    fun testRecyclerViewBehaviour() {
//        //val matchesFragment:MatchesFragment = mainActivityRule.activity.supportFragmentManager.findFragmentById(R.id.main_container) as MatchesFragment
//        //IdlingRegistry.getInstance().register(matchesFragment.getIdlingResourceInTest())
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.mCountingIdlingResource)
//
//        onView(withId(R.id.list_event))
//                .check(matches(isDisplayed()))
//        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
//        onView(withId(R.id.list_event)).perform(
//                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
//
//        //detailActivityRule.
//        //IdlingRegistry.getInstance().register(detailActivityRule.activity.getIdlingResourceInTest())
//        onView(withId(R.id.add_to_favorite))
//                .check(matches(isDisplayed()))
//        onView(withId(R.id.add_to_favorite)).perform(click())
////        onView(withText("Added to favorite"))
////                .check(matches(isDisplayed()))
////        onView(withText("Event is still loading"))
////                .check(matches(isDisplayed()))
//        pressBack()
//    }

}