
# OAuth2 Application

## Endpoints

### 1. `GET /`
Returns `"Hello World"`.

### 2. `GET /secured`
Returns `"Secured"` if authenticated.

### 3. `GET /token`
Returns the OAuth2 token:
- `"ID Token: <token>"` for OIDC users.
- `"Access Token: <token>"` for others.

### 4. `GET /logout`
Logs out the user and returns `"Logged out successfully"`.

## Running the Application
1. Clone the repo.
2. Run with `mvn spring-boot:run` or with your IDE.
3. Access endpoints at `http://localhost:8080`.

## Authentication
- `/secured`, `/token`, and `/logout` require OAuth2 authentication.

