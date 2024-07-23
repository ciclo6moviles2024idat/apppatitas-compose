package pe.idat.apppatitas_compose.auth.domain

import pe.idat.apppatitas_compose.auth.data.network.request.LoginRequest
import pe.idat.apppatitas_compose.auth.data.network.response.LoginResponse
import pe.idat.apppatitas_compose.auth.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository) {

    suspend operator fun invoke(loginRequest: LoginRequest):
            LoginResponse{
        return authRepository.login(loginRequest)
    }
}