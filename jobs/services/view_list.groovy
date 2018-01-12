def viewName = "Internal Services"
def jobFilter = "service-"
 listView(viewName) {
  description("This view is for ${viewName} jobs.")
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
