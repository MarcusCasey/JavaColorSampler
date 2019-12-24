reverseP([], []). %function header
reverseP([X], [X]). %tcreate two new lists
reverseP([H|T], Y) :- reverseP(T,T1), append(T1, [H], Y).%assign list if condition is met
%condition is take tail (last item of list) then append tail to new List T1
%followed by H(the first list item) and assign to new list Y
%repeat until gone through all data members. 

