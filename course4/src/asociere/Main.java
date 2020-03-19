package asociere;

public class Main {
    public  static void main(String[] args) {
        Profesor profesorPrincipalMate = new Profesor(1, "Popescu");
        Profesor profesorSecundarMate = new Profesor(2, "Ionescu");
        Profesor profesorInfo = new Profesor(3, "Anghel");
        Profesor profesorMateSiInfo = new Profesor(4, "Petrescu");
        Profesor profesorInfoSiMate = new Profesor(5, "Andrei");

        Departament departamentInfo = new Departament("info");
        Departament departamentMate = new Departament("mate");

        departamentInfo.setProfesori(new Profesor[]{profesorInfo, profesorMateSiInfo, profesorInfoSiMate});
        departamentMate.setProfesori(new Profesor[]{profesorPrincipalMate, profesorSecundarMate, profesorMateSiInfo, profesorInfoSiMate});

        Universitate unibuc = new Universitate("Unibuc", new Departament[]{departamentInfo, departamentMate});

        System.out.println(unibuc);

        departamentInfo = null;
        System.out.println(unibuc);

        System.out.println(departamentMate);
        departamentMate.getProfesori()[1] = null;
        System.out.println(departamentMate);
        System.out.println(profesorSecundarMate);
    }
}
