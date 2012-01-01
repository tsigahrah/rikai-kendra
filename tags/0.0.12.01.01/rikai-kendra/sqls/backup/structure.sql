-- Table: iso639_3

-- DROP TABLE iso639_3;

CREATE TABLE iso639_3
(
  id character(3) NOT NULL, -- The three-letter 639-3 identifier
  part2b character(3), -- Equivalent 639-2 identifier of the bibliographic applications code set, if there is one
  part2t character(3), -- Equivalent 639-2 identifier of the terminology applications code set, if there is one
  part1 character(2), -- Equivalent 639-1 identifier, if there is one
  scope character(1) NOT NULL, -- I(ndividual), M(acrolanguage), S(pecial)
  type character(1) NOT NULL, -- A(ncient), C(onstructed), E(xtinct), H(istorical), L(iving), S(pecial)
  ref_name character varying(150) NOT NULL, -- Reference language name
  comment character varying(150), -- Comment relating to one or more of the columns
  CONSTRAINT langid PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE iso639_3
  OWNER TO "rikai-macht";
GRANT ALL ON TABLE iso639_3 TO "rikai-macht";
GRANT ALL ON TABLE iso639_3 TO "rikai-bauer";
GRANT SELECT ON TABLE iso639_3 TO "rikai-sucht";
COMMENT ON COLUMN iso639_3.id IS 'The three-letter 639-3 identifier';
COMMENT ON COLUMN iso639_3.part2b IS 'Equivalent 639-2 identifier of the bibliographic applications code set, if there is one';
COMMENT ON COLUMN iso639_3.part2t IS 'Equivalent 639-2 identifier of the terminology applications code set, if there is one';
COMMENT ON COLUMN iso639_3.part1 IS 'Equivalent 639-1 identifier, if there is one';
COMMENT ON COLUMN iso639_3.scope IS 'I(ndividual), M(acrolanguage), S(pecial)';
COMMENT ON COLUMN iso639_3.type IS 'A(ncient), C(onstructed), E(xtinct), H(istorical), L(iving), S(pecial)';
COMMENT ON COLUMN iso639_3.ref_name IS 'Reference language name';
COMMENT ON COLUMN iso639_3.comment IS 'Comment relating to one or more of the columns';

-- Table: kuptimor_varga

-- DROP TABLE kuptimor_varga;

CREATE TABLE kuptimor_varga
(
  sid serial NOT NULL, -- Unique identifier of the class in the semantic database
  parent integer NOT NULL DEFAULT 1, -- SID of parent category, or self SID if top-level
  name character varying(100) NOT NULL, -- Short name of the category in English
  CONSTRAINT kuptimor_varga_pkey PRIMARY KEY (sid ),
  CONSTRAINT fk_sid FOREIGN KEY (parent)
      REFERENCES kuptimor_varga (sid) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE SET DEFAULT
)
WITH (
  OIDS=FALSE
);
ALTER TABLE kuptimor_varga
  OWNER TO "rikai-macht";
COMMENT ON COLUMN kuptimor_varga.sid IS 'Unique identifier of the class in the semantic database';
COMMENT ON COLUMN kuptimor_varga.parent IS 'SID of parent category, or self SID if top-level';
COMMENT ON COLUMN kuptimor_varga.name IS 'Short name of the category in English';


-- Index: ix_parent

-- DROP INDEX ix_parent;

CREATE INDEX ix_parent
  ON kuptimor_varga
  USING hash
  (parent );


  -- Table: kuptimor_varga_description

-- DROP TABLE kuptimor_varga_description;

CREATE TABLE kuptimor_varga_description
(
  vargasid integer NOT NULL, -- reference to the target kuptimor_varga entry
  langid character(3) NOT NULL, -- reference to the target language entry from iso639_3
  description text NOT NULL, -- description in the relevant language
  CONSTRAINT fk_lang FOREIGN KEY (langid)
      REFERENCES iso639_3 (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE NO ACTION,
  CONSTRAINT fk_sid FOREIGN KEY (vargasid)
      REFERENCES kuptimor_varga (sid) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE kuptimor_varga_description
  OWNER TO "rikai-macht";
COMMENT ON COLUMN kuptimor_varga_description.vargasid IS 'reference to the target kuptimor_varga entry';
COMMENT ON COLUMN kuptimor_varga_description.langid IS 'reference to the target language entry from iso639_3';
COMMENT ON COLUMN kuptimor_varga_description.description IS 'description in the relevant language';


-- Index: varga_lang_combination

-- DROP INDEX varga_lang_combination;

CREATE UNIQUE INDEX varga_lang_combination
  ON kuptimor_varga_description
  USING btree
  (vargasid , langid COLLATE pg_catalog."default" );

