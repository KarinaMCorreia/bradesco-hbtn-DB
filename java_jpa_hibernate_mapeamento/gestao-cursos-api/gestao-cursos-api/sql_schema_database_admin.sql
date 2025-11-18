CREATE TABLE Aluno (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT,
    email TEXT
);

CREATE TABLE Endereco (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    logradouro TEXT,
    cidade TEXT,
    estado TEXT,
    aluno_id INTEGER,
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id)
);

CREATE TABLE Telefone (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    numero TEXT,
    tipo TEXT,
    aluno_id INTEGER,
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id)
);

CREATE TABLE Professor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT,
    email TEXT
);

CREATE TABLE MaterialCurso (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    urlMaterial TEXT
);

CREATE TABLE Curso (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT,
    professor_id INTEGER,
    material_id INTEGER,
    FOREIGN KEY (professor_id) REFERENCES Professor(id),
    FOREIGN KEY (material_id) REFERENCES MaterialCurso(id)
);

CREATE TABLE curso_aluno (
    curso_id INTEGER NOT NULL,
    aluno_id INTEGER NOT NULL,
    PRIMARY KEY (curso_id, aluno_id),
    FOREIGN KEY (curso_id) REFERENCES Curso(id),
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id)
);