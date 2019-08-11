# Bus road challenge

## Project structure
 
###Project contains few packages:

- aspect
  * __BusStationsControllerLoggingAspect__ - logging logic for __BusStationsController__
  * __BusStationsFileValidatorLoggingAspect__ - logging logic for __BusStationsFileValidator__
  * __FileOrientedGraphReaderLoggingAspect__ - logging logic for __FileOrientedGraphReader__
- controller
   * __BusStationsController__ - main controller
   * __ResponseExceptionHandler__ - controller advice
- dto
    * __BusRoadResponse__ - response object which uses for __BusStationsController__. Contains  `dep_sid`, `arr_sid` 
    and `direct_bus_road` which says if road exist or not
    * __ErrorResponse__ - response object which uses for controller advice. Contains error message
- exception
    * __IllegalBusStationFileException__ - Custom exception which uses for file reading and validation
- graph
    * __OrientedGraph__ - graph of bus stations which uses for road searching
    * __OrientedGraphInitializer__ - graph initializer
- service
    * __BFSGraphRoadFinder__ - component which uses BFS algorithm for searching a way between two vertexes
    * __BusRoadCheckerImpl__ - main service which uses __BFSGraphRoadFinder__ for inputted params and construct response
    * __BusStationsFileValidator__ - component which validates inputted file with bus stations by validation rules which
    specified in properties
    * __OrientedGraphFileReader__ - component which reads bus stations matrix and convert it into __OrientedGraph__
    * __PropertyHolder__ - component which contains all application properties

