package com.piriurna.tournamentmanager.android

import android.app.Application
import com.piriurna.tournamentmanager.data.api.FifaCupsApi
import com.piriurna.tournamentmanager.data.api.FifaCupsApiImpl
import com.piriurna.tournamentmanager.data.repositories.TournamentRepositoryImpl
import com.piriurna.tournamentmanager.data.services.FirebaseServiceImpl
import com.piriurna.tournamentmanager.domain.repositories.TournamentRepository
import com.piriurna.tournamentmanager.domain.services.FirebaseService
import com.piriurna.tournamentmanager.domain.usecases.AuthenticateUserUseCase
import com.piriurna.tournamentmanager.domain.usecases.CreateTeamUseCase
import com.piriurna.tournamentmanager.domain.usecases.GetNextTournamentForUserUseCase
import com.piriurna.tournamentmanager.domain.usecases.GetTournamentsByDateUseCase
import com.piriurna.tournamentmanager.domain.usecases.GetUserTeamUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

class MyApplication: Application() {

    val firebaseService: FirebaseService by lazy {
        FirebaseServiceImpl()
    }

    val fifaCupsApi: FifaCupsApi by lazy {
        FifaCupsApiImpl(firebaseService)
    }

    val tournamentRepository: TournamentRepository by lazy {
        TournamentRepositoryImpl(fifaCupsApi)
    }

    val createUserUseCase: AuthenticateUserUseCase by lazy {
        AuthenticateUserUseCase(tournamentRepository, firebaseService)
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

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)
    }
}