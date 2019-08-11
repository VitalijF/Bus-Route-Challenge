# Bus road challenge

## Project structure
 
###Project contains few packages:

- aspect
  * [BusStationsControllerLoggingAspect]() - logging logic for [BusStationsController]()
  * [BusStationsFileValidatorLoggingAspect]() - logging logic for [BusStationsFileValidator]()
  * [FileOrientedGraphReaderLoggingAspect]() - logging logic for [FileOrientedGraphReader]()
- controller
   * [BusStationsController]() - main controller
   * [ResponseExceptionHandler]() - controller advice
- dto
    * [BusRoadResponse]() - response object which uses for [BusStationsController](). Contains  `dep_sid`, `arr_sid` 
    and `direct_bus_road` which says if road exist or not
    * [ErrorResponse]() - response object which uses for controller advice. Contains error message
- exception
    * [IllegalBusStationFileException]() - Custom exception which uses for file reading and validation
- graph
    * [OrientedGraph]() - graph of bus stations which uses for road searching
    * [OrientedGraphInitializer]() - graph initializer
- service
    * [BFSGraphRoadFinder]() - component which uses BFS algorithm for searching a way between two vertexes
    * [BusRoadCheckerImpl]() - main service which uses [BFSGraphRoadFinder]() for inputted params and construct response
    * [BusStationsFileValidator]() - component which validates inputted file with bus stations by validation rules which
    specified in properties
    * [OrientedGraphFileReader]() - component which reads bus stations matrix and convert it into [OrientedGraph]()
    * [PropertyHolder]() - component which contains all application properties

