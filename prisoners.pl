%
% Determine a series of steps whereby a sheriff can get three prisoners
% safely from his holding pen to an offshore island prison.
%
% The program will try various steps, but also store the ones that end
% up being on the successful path, so that they can be displayed at the
% end.
%
% Here are the participants:
%
% Clem: he would hurt Barney if the sheriff is not there.
% Barney: he would hurt Arty if the sheriff is not there.
% Arty
% Sheriff Andy: the only one who is trusted to pilot the boat
%
% The boat can only hold three people, including Andy.

% Warmup exercise: a procedure that succeeds if the two given places
% are not the same.
%  not_same_place( Place1, Place2 )
% This is useful because the query "X \= Y" is too broad. We want
% it to only try locations, which are limited to pen and island.
% Therefore this procedure consists only of two ground rules.
%
%%% YOUR CODE HERE %%%
not_same_place(pen, island).
not_same_place(island, pen).

% Diagnostic printing (d-pr).
% Change the definition of dpr/1 to either do nothing or print its argument.
% Here, it is set to print.
%
% dpr(_).
dpr(X) :- print(X).

% Define a "state of the world" structure pos. It has four components:
%
% pos(Clem's location, Barney's loc., Arty's loc., Sheriff Andy's loc.)
%
% For example, pos(pen,island,pen,island) means that Clem and Arty are in
% the holding pen, and Barney and Andy are on the island. (This is safe.)

% The procedure allowed/2 has cases for every state (pos) change that would
% be acceptable under the rules above. The first rule is coded for you. Do
% the rest yourself.

% Note the diagnostic printing. Use that as a format for the other cases.

% The sheriff can take Clem and Barney across the water.
% (All of your cases should include the "Trying" diagnostic printout.
%  The reason it is only "trying" is because the engine may backtrack.
%  The real answer will be in a list of steps, explained below.
% )
%
allowed(pos(Loc1,Loc1,P,Loc1), pos(Loc2,Loc2,P,Loc2)) :-
    not_same_place(Loc1,Loc2),
    dpr('Trying Clem&Barney '),dpr(Loc1),dpr(' to '),dpr(Loc2),dpr('.'),nl.

% The sheriff can take Clem and Arty across the water.
%
%%% YOUR CODE HERE %%%
    allowed(pos(Loc1,P,Loc1,Loc1), pos(Loc2,P,Loc2,Loc2)) :-
        not_same_place(Loc1,Loc2),
        dpr('Trying Clem&Barney '),dpr(Loc1),dpr(' to '),dpr(Loc2),dpr('.'),nl.

% The sheriff can take Barney and Arty across the water.
%
%%% YOUR CODE HERE %%%
    allowed(pos(P,Loc1,Loc1,Loc1), pos(P,Loc2,Loc2,Loc2)) :-
        not_same_place(Loc1,Loc2),
        dpr('Trying Barney&Arty'),dpr(Loc1),dpr(' to '),dpr(Loc2),dpr('.'),nl.


% The sheriff can take Clem across the water.
%
%%% YOUR CODE HERE %%%
     allowed(pos(Loc1,P,P2,Loc1),pos(Loc2,P,P2,Loc2)) :-
        not_same_place(Loc1,Loc2),
        not_same_place(P, P2),
        dpr('Trying Clem&Sheriff'), dpr(Loc1),dpr(' to '),dpr(Loc2),dpr('.'),nl.

% The sheriff can take Barney across the water.
%
%%% YOUR CODE HERE %%%
    allowed(pos(P,Loc1,P,Loc1),pos(P,Loc2,P,Loc2)) :-
        not_same_place(Loc1,Loc2),
        dpr('Trying Barney&Sheriff'),dpr(Loc1),dpr(' to '),dpr(Loc2),dpr('.'),nl.

% The sheriff can take Arty across the water.
%
%%% YOUR CODE HERE %%%
    allowed(pos(P,P2,Loc1,Loc1),pos(P,P2,Loc2,Loc2)) :-
        not_same_place(Loc1,Loc2),
        not_same_place(P,P2),
        dpr('Trying Arty&Sheriff'),dpr(Loc1),dpr(' to '),dpr(Loc2),dpr('.'),nl.


% The sheriff can go by himself as long as Barney is on a different side
% than Clem and Arty.
%
%%% YOUR CODE HERE %%%
    allowed(pos(P,P2,P,Loc1),pos(P,P2,P,Loc2)) :-
        not_same_place(Loc1,Loc2),
        not_same_place(P,P2),
        dpr('Trying Sheriff'),dpr(Loc1),dpr(' to '),dpr(Loc2),dpr('.'),nl.


% Define step/2: a single step in the plan.
%  step( list-of-states-in-plan, updated-list-of-states-in-plan )
% A step may be taken if it is allowed and the new state is not already in
% the list of states that are already a part of this plan.
% In other words the two arguments are identical lists except that the
% second one has a new state (pos) at the head that wasn't already in the list.

%%% YOUR CODE HERE %%%
    step([Y|Z], [D, Y | Z]):- allowed(Y, D), \+member(D, Z).

% Define steps/2: multiple, i.e., transitive closure of, steps in the plan.
%  steps( list-of-states-in-plan, updated-list-of-states-in-plan )

% Steps's arguments are legal if they are the same. (Trivial case)
%

% A steps's arguments are legal if they can be broken down into a single
% step followed by a validated set of steps starting where the new step
% left off. In other words, this involves making up a new intermediate step.
%
%%% YOUR CODE HERE %%%
    steps(Visited,Visited).
    steps(ShortStepList,BigStepList) :- step( ShortStepList, NextList ),
                                        steps( NextList, BigStepList ).

% dump/1
% Print a list of values, one per line, BACKWARDS
%
%%% YOUR CODE HERE %%%
    dump(List) :- accRev(List, [],_).
    accRev([X|T],A,_):- accRev(T,[X|A],_), write(X), nl.
    accRev([],A,A).

% Define the "exported" plan/2 procedure, holding just the start state
% and the goal state, without the lists of states in the plan.
% You do a plan by doing steps from a list containing only the starting
% state to a list contaidning the goal state at the beginning.
% After the steps are found, however, dump them as a side effect.
%
plan(Start,Goal) :- steps([Start],[Goal|Path]),dump([Goal|Path]),nl.


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% Here are some small tests you may want to enter to see if you understand
% the basics of your work.

% Will these print?
% dump([do,ti,la,so,fa,mi,re,do]).

% The sheriff cannot take Clem back to the pen if it leaves Barney
% and Arty alone on the island.
% \+(allowed(pos(island,island,island,island),pos(pen,island,island,pen))).

% The sheriff can take Barney and Arty across the water to the island.
% allowed(pos(pen,pen,pen,pen),pos(pen,island,island,island)).

% Taking the example above, that is a legal step.
% step(
%       [pos(pen,pen,pen,pen)],
%       [pos(pen,island,island,island),pos(pen,pen,pen,pen)]
% ).

% It is also a legal "sequence of steps".
% steps(
%       [pos(pen,pen,pen,pen)],
%       [pos(pen,island,island,island),pos(pen,pen,pen,pen)]
% ).

% Trivial test:
% plan(pos(pen,pen,pen,pen),pos(pen,pen,pen,pen)).
%
% This should succeed in only one way.

% And now the real test:
% plan(pos(pen,pen,pen,pen),pos(island,island,island,island)).
%
% The shortest answer you should see is
%
% pos(pen,pen,pen,pen)
% pos(island,island,pen,island)
% pos(pen,island,pen,pen)
% pos(island,island,island,island)
% 
% Of course there are longer ones.
