import React from 'react';
import { Container, Nav, Navbar, NavDropdown } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
const Footer = () => {
  return (
    <footer>
      <Navbar fixed="bottom" bg="dark" variant="dark" expand="lg" collapseOnSelect>
        <Container>
          <LinkContainer to="/">
            <Navbar.Brand>모두의배달</Navbar.Brand>
          </LinkContainer>
          <Nav className="flex-row">
            <Nav.Item>
              <Nav.Link href="/home">탐색</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link href="/home">모집하기</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link href="/home">내 정보</Nav.Link>
            </Nav.Item>
          </Nav>
        </Container>
      </Navbar>
    </footer>
  );
};

export default Footer;
