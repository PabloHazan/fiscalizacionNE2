

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'fiscalizacionne.Fiscal'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'fiscalizacionne.FiscalRol'
grails.plugin.springsecurity.authority.className = 'fiscalizacionne.Rol'
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
	[pattern: '/fiscal/index',	 access: ['ROLE_ADMIN']],
	[pattern: '/fiscal/edit',	 access: ['ROLE_ADMIN']],
	[pattern: '/fiscal/update',  access: ['ROLE_ADMIN']],
	[pattern: '/fiscal/delete',  access: ['ROLE_ADMIN']],
	[pattern: '/fiscal/*', 		 access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'fiscalizacionne.Token'

