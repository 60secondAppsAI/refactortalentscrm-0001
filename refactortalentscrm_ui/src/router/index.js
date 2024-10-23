import Vue from 'vue'
import VueRouter from 'vue-router'
import Candidates from  '@/pages/Candidates.vue';
import CandidateDetail from  '@/pages/CandidateDetail.vue';
import Jobs from  '@/pages/Jobs.vue';
import JobDetail from  '@/pages/JobDetail.vue';
import Applications from  '@/pages/Applications.vue';
import ApplicationDetail from  '@/pages/ApplicationDetail.vue';
import Interviews from  '@/pages/Interviews.vue';
import InterviewDetail from  '@/pages/InterviewDetail.vue';
import Notes from  '@/pages/Notes.vue';
import NoteDetail from  '@/pages/NoteDetail.vue';
import Recruiters from  '@/pages/Recruiters.vue';
import RecruiterDetail from  '@/pages/RecruiterDetail.vue';
import Assignments from  '@/pages/Assignments.vue';
import AssignmentDetail from  '@/pages/AssignmentDetail.vue';
import Schedules from  '@/pages/Schedules.vue';
import ScheduleDetail from  '@/pages/ScheduleDetail.vue';

Vue.use(VueRouter)

let routes = [
	{
		// will match everything
		path: '*',
		component: () => import('../views/404.vue'),
	},
	{
		path: '/',
		name: 'Home',
			redirect: '/candidates',
									},
	{
		path: '/dashboard',
		name: 'Dashboard',
		layout: "dashboard",
		// route level code-splitting
		// this generates a separate chunk (about.[hash].js) for this route
		// which is lazy-loaded when the route is visited.
		component: () => import(/* webpackChunkName: "dashboard" */ '../views/Dashboard.vue'),
	},
	{
		path: '/layout',
		name: 'Layout',
		layout: "dashboard",
		component: () => import('../views/Layout.vue'),
	},
	{
		path: '/candidates',
		name: 'Candidates',
		layout: "dashboard",
		component: Candidates,
	},
	{
	    path: '/candidate/:candidateId', 
	    name: 'CandidateDetail',
		layout: "dashboard",
	    component: CandidateDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/jobs',
		name: 'Jobs',
		layout: "dashboard",
		component: Jobs,
	},
	{
	    path: '/job/:jobId', 
	    name: 'JobDetail',
		layout: "dashboard",
	    component: JobDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/applications',
		name: 'Applications',
		layout: "dashboard",
		component: Applications,
	},
	{
	    path: '/application/:applicationId', 
	    name: 'ApplicationDetail',
		layout: "dashboard",
	    component: ApplicationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/interviews',
		name: 'Interviews',
		layout: "dashboard",
		component: Interviews,
	},
	{
	    path: '/interview/:interviewId', 
	    name: 'InterviewDetail',
		layout: "dashboard",
	    component: InterviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notes',
		name: 'Notes',
		layout: "dashboard",
		component: Notes,
	},
	{
	    path: '/note/:noteId', 
	    name: 'NoteDetail',
		layout: "dashboard",
	    component: NoteDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/recruiters',
		name: 'Recruiters',
		layout: "dashboard",
		component: Recruiters,
	},
	{
	    path: '/recruiter/:recruiterId', 
	    name: 'RecruiterDetail',
		layout: "dashboard",
	    component: RecruiterDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/assignments',
		name: 'Assignments',
		layout: "dashboard",
		component: Assignments,
	},
	{
	    path: '/assignment/:assignmentId', 
	    name: 'AssignmentDetail',
		layout: "dashboard",
	    component: AssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/schedules',
		name: 'Schedules',
		layout: "dashboard",
		component: Schedules,
	},
	{
	    path: '/schedule/:scheduleId', 
	    name: 'ScheduleDetail',
		layout: "dashboard",
	    component: ScheduleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/requests/quickadd',
		name: 'QuickAdd',
		layout: "dashboard",
		meta: {
			title: 'quickadd',
			sidebarMap: ['applications'],
			breadcrumbs: ['Requests', 'QuickAdd'],
		},
		component: () => import('../pages/QuickAdd.vue'),
	},
	{
		path: '/tables',
		name: 'Tables',
		layout: "dashboard",
		component: () => import('../views/Tables.vue'),
	},
	{
		path: '/billing',
		name: 'Billing',
		layout: "dashboard",
		component: () => import('../views/Billing.vue'),
	},
	{
		path: '/rtl',
		name: 'RTL',
		layout: "dashboard-rtl",
		meta: {
			layoutClass: 'dashboard-rtl',
		},
		component: () => import('../views/RTL.vue'),
	},
	{
		path: '/Profile',
		name: 'Profile',
		layout: "dashboard",
		meta: {
			layoutClass: 'layout-profile',
		},
		component: () => import('../views/Profile.vue'),
	},
	{
		path: '/sign-in',
		name: 'Sign-In',
		component: () => import('../views/Sign-In.vue'),
	},
	{
		path: '/sign-up',
		name: 'Sign-Up',
		meta: {
			layoutClass: 'layout-sign-up',
		},
		component: () => import('../views/Sign-Up.vue'),
	},
]

// Adding layout property from each route to the meta
// object so it can be accessed later.
function addLayoutToRoute( route, parentLayout = "default" )
{
	route.meta = route.meta || {} ;
	route.meta.layout = route.layout || parentLayout ;
	
	if( route.children )
	{
		route.children = route.children.map( ( childRoute ) => addLayoutToRoute( childRoute, route.meta.layout ) ) ;
	}
	return route ;
}

routes = routes.map( ( route ) => addLayoutToRoute( route ) ) ;

const router = new VueRouter({
	mode: 'hash',
	base: process.env.BASE_URL,
	routes,
	scrollBehavior (to, from, savedPosition) {
		if ( to.hash ) {
			return {
				selector: to.hash,
				behavior: 'smooth',
			}
		}
		return {
			x: 0,
			y: 0,
			behavior: 'smooth',
		}
	}
})

export default router
