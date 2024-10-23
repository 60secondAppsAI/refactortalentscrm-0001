<template>
	
	<!-- Main Sidebar -->
	<a-layout-sider
		collapsible
		class="sider-primary"
		breakpoint="lg"
		collapsed-width="0"
		width="250px"
		:collapsed="sidebarCollapsed"
		@collapse="$emit('toggleSidebar', ! sidebarCollapsed)"
		:trigger="null"
		:class="['ant-layout-sider-' + sidebarColor, 'ant-layout-sider-' + sidebarTheme]"
		theme="light"
		:style="{ backgroundColor: 'transparent',}">
			<div class="brand"><img src="images/drc-velocity.png" alt=""> <br><span>refactortalentscrm</span></div>
			<hr>

			<a-menu theme="light" mode="inline"
      			:open-keys="openKeys"
				@openChange="onOpenChange"
				>


	 
			
				<a-menu-item>
					<router-link to="/candidates">
						<span class="label">Candidates</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/jobs">
						<span class="label">Jobs</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/applications">
						<span class="label">Applications</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/interviews">
						<span class="label">Interviews</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/notes">
						<span class="label">Notes</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/recruiters">
						<span class="label">Recruiters</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/assignments">
						<span class="label">Assignments</span>
					</router-link>
				</a-menu-item>
		
				<a-menu-item>
					<router-link to="/schedules">
						<span class="label">Schedules</span>
					</router-link>
				</a-menu-item>
		
	

			</a-menu>

	</a-layout-sider>

</template>

<script>

	export default ({
		props: {
			// Sidebar collapsed status.
			sidebarCollapsed: {
				type: Boolean,
				default: false,
			},
			
			// Main sidebar color.
			sidebarColor: {
				type: String,
				default: "primary",
			},
			
			// Main sidebar theme : light, white, dark.
			sidebarTheme: {
				type: String,
				default: "light",
			},
		},
		data() {
			return {
      			rootSubmenuKeys: [],
				openKeys: [],
			}
		},
		methods: {
			onOpenChange(openKeys)
			{
			
				const latestOpenKey = openKeys.find( key => this.openKeys.indexOf( key ) === -1) ;

				if ( this.rootSubmenuKeys.indexOf( latestOpenKey ) === -1 )
				{
					this.openKeys = openKeys;
				}
				else
				{
					this.openKeys = latestOpenKey ? [ latestOpenKey ] : [] ;
				}
			},
		},
	})

</script>

<style>

.drcv-label {
	padding-left: 65px;
}

</style>
