def commonFile = readFileFromWorkspace('util/global/s1BuildCommon.groovy')
Class s1Common = new GroovyClassLoader(getClass().getClassLoader()).parseClass(commonFile)

def viewName = "Seed Jobs"
def jobFilter = "s1-"
def DSLs = [
        "component-seed":
                [
                    'path': 'jobs/component/jobs.groovy',
                    'url': 'git@git.sami.int.thomsonreuters.com:VenkataPradeep.Karuturi/jenkins_dsl.git',
                    'branch': 'master',
                    'credential': s1Common.sami_git_crednetial_id
                ]
         ,
         
          "ws-seed":
		[
		    'path': 'jobs/ws/jobs.groovy',
		    'url': 'git@git.sami.int.thomsonreuters.com:VenkataPradeep.Karuturi/jenkins_dsl.git',
		    'branch': 'master',
		    'credential': s1Common.sami_git_crednetial_id
                ]
          ,
          "service-seed":
		[
             	    'path': 'jobs/services/jobs.groovy',
	  	    'url': 'git@git.sami.int.thomsonreuters.com:VenkataPradeep.Karuturi/jenkins_dsl.git',
	  	    'branch': 'master',
                    'credential': s1Common.sami_git_crednetial_id
                ]
           ,
          
	  
           "wsadmin-seed":
		[
		    'path': 'jobs/wsadmin/jobs.groovy',
		    'url': 'git@git.sami.int.thomsonreuters.com:VenkataPradeep.Karuturi/jenkins_dsl.git',
		    'branch': 'master',
		    'credential': s1Common.sami_git_crednetial_id
                ]
   ]

DSLs.each { name, DSL ->

    def jobname = "s1-" + name

    freeStyleJob(jobname) {


        scm {
	            git {
	                extensions { cleanBeforeCheckout() }
	                remote {
	                    url(DSL.url)
	                    branch(DSL.branch)
	                    credentials(DSL.credential)
	                }
	            }
        }

        steps {
            dsl {
                external(readFileFromWorkspace(DSL.path))
                removeAction('DELETE')
                removeViewAction('IGNORE')
            }
        }
    }
  }

 listView(viewName) {
  description("This view is for ${viewName} jobs.  It is autopopulated with any jobs that have 'S1' in their description.")
  jobs {
    jobFilters{
      regex {
        matchValue(RegexMatchValue.NAME)
        regex(jobFilter+".*")
      }
    }
  }

  columns {
    status()
    weather()

    name()
    lastSuccess()
    lastFailure()
    lastDuration()
    buildButton()
  }
 }
