//For git sparse Checkout
	 scm
    {
            git
            {
                remote{
                url('git@'+s1Common.GIT_BASE_URL+':VenkataPradeep.Karuturi/jenkins_dsl.git')
		branch('*/master')
		credentials(s1Common.sami_git_crednetial_id)
		      }
	   
		configure { git ->
                git / 'extensions' / 'hudson.plugins.git.extensions.impl.SparseCheckoutPaths' {
					sparseCheckoutPaths {
                    ['scripts/'].each { mypath ->
                        'hudson.plugins.git.extensions.impl.SparseCheckoutPath' {
                            path("${mypath}")
                            }
                        }
                   }
			    }
             }
          }
	}
