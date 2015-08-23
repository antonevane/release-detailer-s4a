angular.module( 'ngBoilerplate.release', [
        'ui.router',
        'ngResource',
        'base64'
    ])
    .config(function($stateProvider) {
        $stateProvider
            .state('releaseSearch', {
                url:'/releases/search',
                views: {
                    'main': {
                        templateUrl:'release/search.tpl.html',
                        controller:'ReleaseSearchCtrl'
                    }
                },
                data : {
                    pageTitle : "Search Releases"
                }
            })
            .state('releaseDetails', {
                url:'/releases/details/{rtn}',
                views: {
                    'main': {
                        templateUrl:'release/details.tpl.html',
                        controller:'ReleaseDetailsCtrl'
                    }
                },
                resolve: {
                    release: function(releaseService, $stateParams) {
                        returnValue = releaseService.getMaReleaseByRtn($stateParams.rtn);
                        return returnValue;
                    }
                },
                data: {
                    pageTitle : "Release Details"
                }
            })
            .state('releaseLocationSearch', {
                url:'/releases/locationSearch',
                views: {
                    'main': {
                        templateUrl:'release/location-search.tpl.html',
                        controller:'ReleaseSearchCtrl'
                    }
                },
                data : {
                    pageTitle : "Search Releases by Town"
                }
            })
        ;
    })
    .factory('releaseService', function($resource) {
        var service = {};
        service.getMaReleaseById = function(releaseId) {
            var Release = $resource("/release-detailer-s4a/rest/releases/:paramReleaseId");
            return Release.get({paramReleaseId : releaseId}).$promise;
        };
        service.getMaReleaseByRtn = function(rtn, success, failure) {
            var Release = $resource("/release-detailer-s4a/rest/releases/ma/:paramRtn");
            return Release.get({paramRtn : rtn}).$promise;
        };
        service.getMaReleasesByTown = function(town) {
            console.log("releaseService.getMaReleasesByTown() called: " + town);
            var Release = $resource("/release-detailer-s4a/rest/releases/ma/town/:paramTown");
            return Release.get({paramTown : town}).$promise;
        };
        service.getMaReleasesByTownPaged = function(town, offset, limit) {
            var Release = $resource("/release-detailer-s4a/rest/releases/ma?town=:paramTown&offset=:paramOffset&limit=:paramLimit");
            return Release.get({paramTown : town, paramOffset : offset, paramLimit : limit}).$promise;
        };
        service.getMaReleasesPagination = function(paginationLink) {
            var Release = $resource(paginationLink);
            return Release.get().$promise;
        };
        return service;
    })
    .controller("ReleaseSearchCtrl", function($scope, releaseService) {
        $scope.getMaReleaseByRtn = function(rtn) {
            // Get the $promise from the service to fetch the release information using the RTN
            releaseService.getMaReleaseByRtn(rtn).then(
                function(data) {
                    console.log("ReleaseSearchCtrl: getMaReleaseByRtn: data", data);
                    $scope.release = data;
                },
                function() {
                    console.log("Unable to get release information for " + $scope.rtn);
                    $scope.release = null;
                });
        };
        $scope.getMaReleaseById= function(rid) {
            // Get the $promise from the service to fetch the release information using the internal release ID
            releaseService.getMaReleaseById(rid).then(
                function(data) {
                    console.log("ReleaseSearchCtrl: getMaReleaseById: data", data);
                    $scope.release = data;
                },
                function() {
                    console.log("Unable to get release information for " + $scope.rtn);
                    $scope.release = null;
                });
        };
        $scope.getMaReleasesByTown = function(town) {
            console.log("ReleaseSearchCtrl: getMaReleasesByTown: ", town);
            releaseService.getMaReleasesByTown(town).then(
                function(data) {
                    console.log("SUCCESS: " + data);
                    $scope.releases = data.releases;
                    console.log("$scope.releases: " + $scope.releases);
                },
                function(error) {
                    console.log("FAILURE: " + error);
                }
            );

        };
        $scope.getMaReleasesByTownPaged = function(town, offset, limit) {
            releaseService.getMaReleasesByTownPaged(town, offset, limit).then(
                function(data) {
                    console.log("SUCCESS: " + data);
                    $scope.releases = data.releases;
                    $scope.next = data.links.next;
                    $scope.prev = data.links.prev;
                    console.log("$scope.releases: " + $scope.releases);
                },
                function(error) {
                    console.log("FAILURE: " + error);
                }
            );

        };
        $scope.getMaReleasesPagination = function(paginationLink) {
            releaseService.getMaReleasesPagination(paginationLink).then(
                function(data) {
                    console.log("SUCCESS: " + data);
                    $scope.releases = data.releases;
                    $scope.next = data.links.next;
                    $scope.prev = data.links.prev;
                    console.log("$scope.releases: " + $scope.releases);
                },
                function(error) {
                    console.log("FAILURE: " + error);
                }
            );

        };
        $scope.towns = ['Abington', 'Acton', 'Acushnet', 'Adams', 'Agawam', 'Alford', 'Amesbury', 'Amherst', 'Andover', 'Aquinnah', 'Arlington', 'Ashburnham', 'Ashby', 'Ashfield', 'Ashland', 'Athol', 'Attleboro', 'Auburn', 'Avon', 'Ayer', 'Barnstable', 'Barre', 'Becket', 'Bedford', 'Belchertown', 'Bellingham', 'Belmont', 'Berkley', 'Berlin', 'Bernardston', 'Beverly', 'Billerica', 'Blackstone', 'Blandford', 'Bolton', 'Boston', 'Bourne', 'Boxborough', 'Boxford', 'Boylston', 'Braintree', 'Brewster', 'Bridgewater', 'Brimfield', 'Brockton', 'Brookfield', 'Brookline', 'Buckland', 'Burlington', 'Cambridge', 'Canton', 'Carlisle', 'Carver', 'Charlemont', 'Charlton', 'Chatham', 'Chelmsford', 'Chelsea', 'Cheshire', 'Chester', 'Chesterfield', 'Chicopee', 'Chilmark', 'Clarksburg', 'Clinton', 'Cohasset', 'Colrain', 'Concord', 'Conway', 'Cummington', 'Dalton', 'Danvers', 'Dartmouth', 'Dedham', 'Deerfield', 'Dennis', 'Dighton', 'Douglas', 'Dover', 'Dracut', 'Dudley', 'Dunstable', 'Duxbury', 'East Bridgewater', 'East Brookfield', 'East Longmeadow', 'Eastham', 'Easthampton', 'Easton', 'Edgartown', 'Egremont', 'Erving', 'Essex', 'Everett', 'Fairhaven', 'Fall River', 'Falmouth', 'Fitchburg', 'Florida', 'Foxborough', 'Framingham', 'Franklin', 'Freetown', 'Gardner', 'Georgetown', 'Gill', 'Gloucester', 'Goshen', 'Gosnold', 'Grafton', 'Granby', 'Granville', 'Great Barrington', 'Greenfield', 'Groton', 'Groveland', 'Hadley', 'Halifax', 'Hamilton', 'Hampden', 'Hancock', 'Hanover', 'Hanson', 'Hardwick', 'Harvard', 'Harwich', 'Hatfield', 'Haverhill', 'Hawley', 'Heath', 'Hingham', 'Hinsdale', 'Holbrook', 'Holden', 'Holland', 'Holliston', 'Holyoke', 'Hopedale', 'Hopkinton', 'Hubbardston', 'Hudson', 'Hull', 'Huntington', 'Ipswich', 'Kingston', 'Lakeville', 'Lancaster', 'Lanesborough', 'Lawrence', 'Lee', 'Leicester', 'Lenox', 'Leominster', 'Leverett', 'Lexington', 'Leyden', 'Lincoln', 'Littleton', 'Longmeadow', 'Lowell', 'Ludlow', 'Lunenburg', 'Lynn', 'Lynnfield', 'Malden', 'Manchester By The Sea', 'Mansfield', 'Marblehead', 'Marion', 'Marlborough', 'Marshfield', 'Mashpee', 'Mattapoisett', 'Maynard', 'Medfield', 'Medford', 'Medway', 'Melrose', 'Mendon', 'Merrimac', 'Methuen', 'Middleborough', 'Middlefield', 'Middleton', 'Milford', 'Millbury', 'Millis', 'Millville        ', 'Milton', 'Monroe', 'Monson', 'Montague', 'Monterey', 'Montgomery', 'Mount Washington', 'Nahant', 'Nantucket', 'Natick', 'Needham', 'New Ashford', 'New Bedford', 'New Braintree', 'New Marlborough', 'New Salem', 'Newbury', 'Newburyport', 'Newton', 'Norfolk', 'North Adams', 'North Andover', 'North Attleborou', 'North Brookfield', 'North Reading', 'Northampton', 'Northborough', 'Northbridge', 'Northfield', 'Norton', 'Norwell', 'Norwood', 'Oak Bluffs', 'Oakham', 'Orange', 'Orleans', 'Otis', 'Oxford', 'Palmer', 'Paxton', 'Peabody', 'Pelham', 'Pembroke', 'Pepperell', 'Peru', 'Petersham', 'Phillipston', 'Pittsfield', 'Plainfield', 'Plainville', 'Plymouth', 'Plympton', 'Princeton', 'Provincetown', 'Quincy', 'Randolph', 'Raynham', 'Reading', 'Rehoboth', 'Revere', 'Richmond', 'Rochester', 'Rockland', 'Rockport', 'Rowe', 'Rowley', 'Russell', 'Rutland', 'Salem', 'Salisbury', 'Sandisfield', 'Sandwich', 'Saugus', 'Savoy', 'Scituate', 'Seekonk', 'Sharon', 'Sheffield', 'Shelburne', 'Sherborn', 'Shirley', 'Shrewsbury', 'Shutesbury', 'Somerset', 'Somerville', 'South Hadley', 'Southampton', 'Southborough', 'Southbridge', 'Southwick', 'Spencer', 'Springfield', 'Sterling', 'Stockbridge', 'Stoneham', 'Stoughton', 'Stow', 'Sturbridge', 'Sudbury', 'Sunderland', 'Sutton', 'Swampscott', 'Swansea', 'Taunton', 'Templeton', 'Tewksbury', 'Tisbury', 'Tolland', 'Topsfield', 'Townsend', 'Truro', 'Tyngsborough', 'Tyringham', 'Upton', 'Uxbridge', 'Wakefield', 'Wales', 'Walpole', 'Waltham', 'Ware', 'Wareham', 'Warren', 'Warwick', 'Washington', 'Watertown', 'Wayland', 'Webster', 'Wellesley', 'Wellfleet', 'Wendell', 'Wenham', 'West Boylston', 'West Bridgewater', 'West Brookfield', 'West Newbury', 'West Springfield', 'West Stockbridge', 'West Tisbury', 'Westborough', 'Westfield', 'Westford', 'Westhampton', 'Westminster', 'Weston', 'Westport', 'Westwood', 'Weymouth', 'Whately', 'Whitman', 'Wilbraham', 'Williamsburg', 'Williamstown', 'Wilmington', 'Winchendon', 'Winchester', 'Windsor', 'Winthrop', 'Woburn', 'Worcester', 'Worthington', 'Wrentham', 'Yarmouth'];
        $scope.startsWith = function(town, viewValue) {
            return town.substring(0, viewValue.length).toLowerCase() == viewValue.toLowerCase();
        };
    })
    .controller("ReleaseDetailsCtrl", function($scope, releaseService, release) {
        console.log("release as argument to controller: release = ", release);
        $scope.release = release;

    })
;