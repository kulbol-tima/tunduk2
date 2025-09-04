CREATE TABLE applications (
    id BIGSERIAL PRIMARY KEY,
    registration_number VARCHAR(255) NOT NULL UNIQUE,
    application_date DATE NOT NULL,
    applicant_full_name VARCHAR(255) NOT NULL,
    applicant_pin VARCHAR(14) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    passport_series VARCHAR(255),
    passport_number VARCHAR(255),
    family_status VARCHAR(255),
    previous_benefit BOOLEAN,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE TABLE family_members (
    id BIGSERIAL PRIMARY KEY,
    application_id BIGINT NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    pin VARCHAR(14) NOT NULL UNIQUE,
    passport VARCHAR(255),
    relationship VARCHAR(255) NOT NULL,
    citizenship VARCHAR(255) NOT NULL,
    CONSTRAINT fk_family_members_application FOREIGN KEY (application_id) REFERENCES applications(id)
);

CREATE TABLE recipients (
    id BIGSERIAL PRIMARY KEY,
    application_id BIGINT NOT NULL UNIQUE,
    benefit_amount NUMERIC(19, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    region_coefficient NUMERIC(19, 2),
    additional_coefficient NUMERIC(19, 2),
    bonus NUMERIC(19, 2),
    status VARCHAR(255) NOT NULL,
    CONSTRAINT fk_recipients_application FOREIGN KEY (application_id) REFERENCES applications(id)
);

CREATE TABLE payments (
    id BIGSERIAL PRIMARY KEY,
    recipient_id BIGINT NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    payment_date DATE NOT NULL,
    bank_account VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    CONSTRAINT fk_payments_recipient FOREIGN KEY (recipient_id) REFERENCES recipients(id)
);
