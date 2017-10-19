

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fiscalizacionne.Fiscal'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fiscalizacionne.FiscalRol'
grails.plugin.springsecurity.authority.className = 'fiscalizacionne.Rol'


grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'
grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'fiscalizacionne.TokenRest'
grails.plugin.springsecurity.rest.token.storage.gorm.tokenValuePropertyName = 'token'
grails.plugin.springsecurity.rest.token.storage.gorm.usernamePropertyName = 'username'
grails.plugin.springsecurity.rest.login.useJsonCredentials = true
grails.plugin.springsecurity.rest.login.failureStatusCode = 403
grails.plugin.springsecurity.logout.postOnly = false


grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],

	[pattern: '/fiscal/index',	 access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],
	[pattern: '/fiscal/edit',	 access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],
	[pattern: '/fiscal/update',  access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],
	[pattern: '/fiscal/delete',  access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],
	[pattern: '/fiscal/editPass',access: 'isAuthenticated()'],
	[pattern: '/fiscal/*',		 access: ['permitAll']],

	[pattern: '/mesa/*',		 access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],
	[pattern: '/escuela/*',		 access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],

	[pattern: '/comuna/create',  access: ['ROLE_ADMIN']],
	[pattern: '/comuna/save',	 access: ['ROLE_ADMIN']],
	[pattern: '/comuna/delete',  access: ['ROLE_ADMIN']],
	[pattern: '/comuna/*',		 access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],

	[pattern: 'fuerzaPolitica/getSelectFuerzasByComuna*', access: ['ROLE_ADMIN', 'ROLE_ADMIN_COMUNA']],
	[pattern: '/fuerzaPolitica/*',access: ['ROLE_ADMIN']],
	[pattern: '/partido/*',access: ['ROLE_ADMIN']],


	[pattern: '/fiscalApi/getResultados', access: ['permitAll']],
	[pattern: '/fiscalApi/*',	 access: ['ROLE_FISCAL_GENERAL', 'ROLE_FISCAL_MESA']],
	[pattern: '/api/login',		 access:['permitAll']],
	[pattern: '/api/validate',	 access:['permitAll']],
	[pattern: '/api/logout',	 access:['permitAll']],
	[pattern: '/*', 		 	 access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/**api/**', 		 filters: 'JOINED_FILTERS,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'],
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'fiscalizacionne.Token'

