Obiectele utilizate: Person (Student | Employees (Teacher | AuxiliaryStaff)), Departments, Class = ClassOfStudents, Hour

Posibile interogari:
0. Ce ore are un profesor (toate, intr+o zi anume, intr+un interval anume) - Teacher
1. Ce ore are o clasa     (toate, intr+o zi anume, intr+un interval anume) - ClassOfStudents
2. Care sunt colegii (de clasa) a unui student - Student & Class
3. Care este clasa cu numarul cel mai mare de studenti - Student & Class
4. Care este cel mai bine platit angajat si daca este profor ce materie preda - Employees & Teachers & AuxiliaryStaff
5. Care este cea mai invarsta persoana din facultate - Person (Student & Teacher & AuxiliaryStaff)
6. Care este departamentul cu cu cele mai multe persoane tinere (varsta <= Suma varsteleor angajatilor / 4) - Employees (Teachers & AuxiliaryStaff) & Departments
7. Sa se odoneze clase dupa media de incepere a orelor si sa fie afisate maxim primele 5 - Class & Hour
8. Care este profesorul care sta cel mai mult in facultate (consideram ca nu pleaca din facultate intre prima si ultima lui ora dintr+o zi) - Teachers & Hour
9. Care este clasa care sta cel mai mult degeaba in facultate (suma ferestrelor considerand ca nu se pleaca din facultate intre prima si ultima ora dintr+o zi) - Class & Hour
