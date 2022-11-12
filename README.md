# MOvie Booking and LIsting Management Application (MOBLIMA)

About
----------------------
Assignment Project for SC2002(Object-Oriented Design & Programming)
Nanyang Technological University


Problem Definition
----------------------
MOBLIMA is an application to computerize the processes of making online booking and purchase of movie tickets, listing of movies and sale reporting. It will be used by the moviegoers and cinema staff. The application acts as a centralized ‘location’ for making bookings for all the Cineplexes in different locations managed by the vendor. 

Required following functionalities:
----------------------
- ### Movie-goers
  - Movie listings and showtimes can be queried and booking can be made.
  - From the movie listings, movie-goer can also view the information about the movie.
    - Movie title
    - Showing status [Coming Soon, Preview, Now Showing]
    - SYNOPSIS – movie abstract
    - Director
    - Cast (at least 2)
    - Overall reviewer rating (1 – 5 [best])
    - Past reviews and reviewers’ ratings
  - A layout of each cinema will be presented for seat selections upon booking.
  - Upon booking, the application will capture the movie-goer’s name, mobile number and email address. Each payment will have a transaction id (TID)
  - Movie-goer can check their history of bookings. 
  - Movie-goer can also enter his/her review and rating
  
- ### Cinema Staff
  - Cinema staff needs to login to access its functions
  - Cinema staff can configure the system settings (e.g., ticket prices, holidays, etc.)
    - Movie title
    - Showing status [Coming Soon, Preview, Now Showing]
    - SYNOPSIS – movie abstract
    - Director
    - Cast (at least 2)
    - Overall reviewer rating (1 – 5 [best])
    - Past reviews and reviewers’ ratings
  - Cinema staff can enter the forthcoming movies, its type (Blockbuster/3D,etc.), movie rating (e.g. PG), show times, the cinema, showing status (Coming Soon, Preview, Now Showing, End Of Showing), etc.
  - Cinema staff can also update the details of the movies or remove the movie by changing the status to ‘End of Showing’. 
  - Cinema staff can list the current top 5 ranking movies by
    - Ticket sales (display the movie title and total sales)
    - Overall reviewers’ rating (display the movie title and overall rating)


<!-- ROADMAP -->
## Roadmap

 
 - [x]  **_Admin module_**
    - [x] Movie Listing
        - [x] Create
        - [x] Update
        - [x] Remove
    - [x] Cinema Showtimes & Movies Shown
        - [x] Create
        - [x] Update
        - [x] Remove
    - [x] Configure System Settings
 - [x] **_Movie-goer module_**
    - [x] Search/List movie
    - [x] View movie details – including reviews and ratings
    - [x] Check seat availability and selection of seat/s.
    - [x] Book and purchase ticket
    - [x] View booking history
    - [x] List the Top 5 ranking by ticket sales OR by overall reviewers’ ratings



<!-- Contribution 
## Contribution
Make sure to have GitBash installed  https://git-scm.com/downloads -->



Repository Description
----------------------

This repository is submitted to Nanyang Technological University as part of a 
graded assignment for the course SC2002 (Object-Oriented Design & Programming).


    
