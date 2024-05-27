package com.piriurna.tournamentmanager.android

import android.app.Application
import com.piriurna.tournamentmanager.fifacups.data.api.FifaCupsApi
import com.piriurna.tournamentmanager.fifacups.data.api.FifaCupsApiImpl
import com.piriurna.tournamentmanager.fifacups.data.repositories.TournamentRepositoryImpl
import com.piriurna.tournamentmanager.fifacups.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.fifacups.domain.usecases.CreateTeamUseCase
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetLoggedInUserUseCase
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetNextTournamentForUserUseCase
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetTournamentsByDateUseCase
import com.piriurna.tournamentmanager.fifacups.domain.usecases.GetUserTeamUseCase
import com.piriurna.tournamentmanager.firebase.data.api.FirebaseApiImpl
import com.piriurna.tournamentmanager.firebase.data.repositories.FirebaseRepositoryImpl
import com.piriurna.tournamentmanager.firebase.domain.api.FirebaseApi
import com.piriurna.tournamentmanager.firebase.domain.repositories.FirebaseRepository
import com.piriurna.tournamentmanager.login.domain.usecases.LoginUserUseCase
import com.piriurna.tournamentmanager.login.domain.usecases.RegisterUserUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

class MyApplication: Application() {

    val firebaseApi: FirebaseApi by lazy {
        FirebaseApiImpl()
    }

    val firebaseRepository: FirebaseRepository by lazy {
        FirebaseRepositoryImpl(firebaseApi)
    }

    val fifaCupsApi: FifaCupsApi by lazy {
        FifaCupsApiImpl(firebaseApi)
    }

    val tournamentRepository: TournamentRepository by lazy {
        TournamentRepositoryImpl(fifaCupsApi)
    }

    val createUserUseCase: RegisterUserUseCase by lazy {
        RegisterUserUseCase(tournamentRepository, firebaseRepository)
    }

    val loginUserUseCase: LoginUserUseCase by lazy {
        LoginUserUseCase(tournamentRepository, firebaseRepository)
    }
    val createTeamUseCase: CreateTeamUseCase by lazy {
        CreateTeamUseCase(tournamentRepository)
    }
    val getNextTournamentForUserUseCase: GetNextTournamentForUserUseCase by lazy {
        GetNextTournamentForUserUseCase(tournamentRepository)
    }
    val getUserTeamUseCase: GetUserTeamUseCase by lazy {
        GetUserTeamUseCase(tournamentRepository)
    }
    val getTournamentsByDateUseCase: GetTournamentsByDateUseCase by lazy {
        GetTournamentsByDateUseCase(tournamentRepository)
    }
    val getLoggedInUserUseCase: GetLoggedInUserUseCase by lazy {
        GetLoggedInUserUseCase(tournamentRepository)
    }

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)
    }
}