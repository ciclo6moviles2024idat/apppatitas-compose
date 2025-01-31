package pe.idat.apppatitas_compose.auth.data.repository

import pe.idat.apppatitas_compose.auth.data.network.request.LoginRequest
import pe.idat.apppatitas_compose.auth.data.network.request.RegistroRequest
import pe.idat.apppatitas_compose.auth.data.network.response.LoginResponse
import pe.idat.apppatitas_compose.auth.data.network.response.RegistroResponse
import pe.idat.apppatitas_compose.auth.data.network.service.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService) {

    suspend fun login (loginRequest: LoginRequest): LoginResponse{
        return authService.login(loginRequest)
    }

    suspend fun registro(registroRequest: RegistroRequest): RegistroResponse{
        return authService.registro(registroRequest)
    }
}